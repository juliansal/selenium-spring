package io.happykraken.basic.reports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MessagesReader implements IMessagesReader {
	ObjectMapper mapper = new ObjectMapper();
	public final List<Map<?, ?>> stepIdentifiers = new ArrayList<>();

	public List<Map<?, ?>> getStepIdentifiers() {
		return stepIdentifiers;
	}

	@Override
	public List<String> readMessagesLog() throws URISyntaxException, IOException {
		Path path = Paths.get(Objects
				.requireNonNull(
						getClass()
								.getClassLoader()
								.getResource("message.txt"))
				.toURI()
		);
		BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
		List<String> messages = reader.lines().collect(Collectors.toList());
		reader.close();

		return messages;
	}

	@Override
	public void addToStepIdentifiersMap(String tCase) {
		try {
			JsonNode allTestCases = mapper.readValue(tCase, JsonNode.class)
					.with("testCase")
					.withArray("testSteps");

			for (int i = 0; i < allTestCases.size(); i++) {
				JsonNode currentStep = allTestCases.get(i);

				if (currentStep.withArray("stepDefinitionIds").size() > 0) {
					stepIdentifiers.add(Map.of(
							"stepId", currentStep
									.get("id").asText(),
							"stepDefId", currentStep
									.withArray("stepDefinitionIds")
									.get(0).asText()
					));
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String findStepName(String line) {
		try {
			JsonNode msg = mapper.readValue(line, JsonNode.class);
			return msg
					.with("stepDefinition")
					.with("pattern")
					.get("source").textValue();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String findStepDuration(String finishedTestStep) {
		try {
			JsonNode msg = mapper.readValue(finishedTestStep, JsonNode.class);
			int nanoScale = 1_000_000_000;
			long nanos = msg
					.with("testStepFinished")
					.with("testStepResult")
					.with("duration").get("nanos").intValue();
			int seconds = msg
					.with("testStepFinished")
					.with("testStepResult")
					.with("duration").get("seconds").intValue();
			float nanosToSeconds = seconds + ((float) nanos / nanoScale);

			return Float.toString(nanosToSeconds);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String findTestCaseMessage(List<String> messages) {
		return messages
				.stream()
				.filter(line -> line.contains("testCase"))
				.filter(line -> {
					try {
						return mapper
								.readValue(line, JsonNode.class)
								.has("testCase");
					} catch (JsonProcessingException e) {
						e.printStackTrace();
						return false;
					}
				})
				.findFirst()
				.orElse("");
	}

	@Override
	public String findStepFinishedMessage(List<String> messages, Map<?, ?> stepStats) {
		return messages
				.stream()
				.filter(line -> line.contains("testStepFinished"))
				.filter(line -> {
					try {
						JsonNode msg = mapper.readValue(line, JsonNode.class);
						if (!msg.has("testStepFinished")) return false;
						return msg
								.with("testStepFinished")
								.get("testStepId").textValue()
								.equals(stepStats.get("stepId"));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
						return false;
					}
				})
				.findFirst()
				.orElse("");
	}

	@Override
	public String findStepMessage(List<String> messages, Map<?, ?> stepStats) {
		return messages
				.stream()
				.filter(line -> line.contains("stepDefinition"))
				.filter(line -> {
					try {
						JsonNode msg = mapper.readValue(line, JsonNode.class);
						if (!msg.has("stepDefinition")) return false;
						return msg
								.with("stepDefinition")
								.get("id").textValue()
								.equals(stepStats.get("stepDefId"));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
						return false;
					}
				})
				.findFirst()
				.orElse("");
	}
}
