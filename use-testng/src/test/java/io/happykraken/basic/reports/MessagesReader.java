package io.happykraken.basic.reports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.log4testng.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class MessagesReader {
	private static Logger logger = Logger.getLogger(Logger.class);
	ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		MessagesReader reader = new MessagesReader();
		try {
			reader.read();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void read() throws IOException, URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource("message.txt").toURI());
		BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
		List<String> messages = reader.lines().collect(Collectors.toList());
		reader.close();
		Map stepStats = new HashMap<>();
		List<Map> stepsMap = new ArrayList<>();

		List<Map> altStepStats = new ArrayList<>();
		List<Map> altStepMap = new ArrayList<>();

		findTestCase(messages, stepStats)
				.ifPresent(tCase -> {
					try {
						JsonNode allTestCases = mapper.readValue(tCase, JsonNode.class)
								.with("testCase")
								.withArray("testSteps");

						for (int i = 0; i < allTestCases.size(); i++) {
							JsonNode currentStep = allTestCases.get(i);

							if (currentStep.withArray("stepDefinitionIds").size() > 0) {
								altStepStats.add(Map.of(
										"stepId", currentStep
												.get("id").asText(),
										"stepDefId", currentStep
												.withArray("stepDefinitionIds")
												.get(0).asText()
								));
							}
						}

						stepStats.put("stepId", allTestCases.get(0)
								.get("id").asText());
						stepStats.put("stepDefId", allTestCases.get(0)
								.withArray("stepDefinitionIds").get(0).asText());
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});

		altStepStats
				.forEach(step -> {
					findStepDefinition(messages, stepStats)
							.ifPresent(line -> {
								try {
									JsonNode msg = mapper.readValue(line, JsonNode.class);
									stepStats.put("step", msg
											.with("stepDefinition")
											.with("pattern")
											.get("source").textValue());
								} catch (JsonProcessingException e) {
									e.printStackTrace();
								}
							});

					findTestStepFinished(messages, stepStats)
							.ifPresent(line -> {
								try {
									JsonNode msg = mapper.readValue(line, JsonNode.class);
									stepStats.put("seconds", msg
											.with("testStepFinished")
											.with("testStepResult")
											.with("duration").get("seconds").intValue());
									stepStats.put("nanos", msg
											.with("testStepFinished")
											.with("testStepResult")
											.with("duration").get("nanos").intValue());
								} catch (JsonProcessingException e) {
									e.printStackTrace();
								}
							});
				});

		findStepDefinition(messages, stepStats)
				.ifPresent(line -> {
					try {
						JsonNode msg = mapper.readValue(line, JsonNode.class);
						stepStats.put("step", msg
								.with("stepDefinition")
								.with("pattern")
								.get("source").textValue());
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});

		findTestStepFinished(messages, stepStats)
				.ifPresent(line -> {
					try {
						JsonNode msg = mapper.readValue(line, JsonNode.class);
						stepStats.put("seconds", msg
								.with("testStepFinished")
								.with("testStepResult")
								.with("duration").get("seconds").intValue());
						stepStats.put("nanos", msg
								.with("testStepFinished")
								.with("testStepResult")
								.with("duration").get("nanos").intValue());
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});

		stepsMap.add(Map.of(
				"step", stepStats.get("step"),
				"duration", Map.of(
						"seconds", stepStats.get("seconds"),
						"nanos", stepStats.get("nanos"))
		));

		for (int i = 0; i < altStepStats.size(); i++) {
			String finished = findTestStepFinished(messages, altStepStats.get(i)).get();
			Map step = stepMap(finished);
			String def = findStepDefinition(messages, altStepStats.get(i)).get();
			String stepName = stepDefinition(def);

			altStepMap.add(Map.of(
					"step", stepName,
					"duration", Map.of(
							"seconds", step.get("seconds"),
							"nanos", step.get("nanos"))
			));
		}

		System.out.println("stepStats: " + mapper.writeValueAsString(stepStats));
		System.out.println("altStepStats: " + mapper.writeValueAsString(altStepStats));
		System.out.println("stepsMap: " + mapper.writeValueAsString(stepsMap));
		System.out.println("altStepMap: " + mapper.writeValueAsString(altStepMap));
	}

	public String stepDefinition(String line) {
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

	public Map stepMap(String finishedTestStep) {
		try {
			JsonNode msg = mapper.readValue(finishedTestStep, JsonNode.class);
			return Map.of("seconds", msg
					.with("testStepFinished")
					.with("testStepResult")
					.with("duration").get("seconds").intValue(),
					"nanos", msg
							.with("testStepFinished")
							.with("testStepResult")
							.with("duration").get("nanos").intValue());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	Optional<String> findTestCase(List<String> messages, Map stepStats) {
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
				.findFirst();
	}

	Optional<String> findTestStepFinished(List<String> messages, Map stepStats) {
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
				.findFirst();
	}

	Optional<String> findStepDefinition(List<String> messages, Map stepStats) {
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
				.findFirst();
	}

	void showAllStepDefinitions(BufferedReader reader) {

		reader
				.lines()
				.filter(line -> line.contains("stepDefinition"))
				.forEach(line -> {
					try {
						JsonNode msg = mapper.readValue(line, JsonNode.class);
						if (msg.has("stepDefinition")) {
							logger.info(msg
									.with("stepDefinition")
									.get("pattern")
									.get("source")
							);
						}
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				});
	}
}
