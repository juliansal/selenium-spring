package io.happykraken.basic.reports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.messages.types.TestCase;

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

public class CucumberMessagesReader implements IMessagesReader {
	ObjectMapper mapper = new ObjectMapper();
	public final List<Map<?, ?>> stepIdentifiers = new ArrayList<>();

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

	}

	@Override
	public String findStepName(String line) {
		return null;
	}

	@Override
	public String findStepDuration(String finishedTestStep) {
		return null;
	}

	@Override
	public String findTestCaseMessage(List<String> messages) {
		return null;
	}

	@Override
	public String findStepFinishedMessage(List<String> messages, Map<?, ?> stepStats) {
		return null;
	}

	@Override
	public String findStepMessage(List<String> messages, Map<?, ?> stepStats) {
		return null;
	}

	@Override
	public List<Map<?, ?>> getStepIdentifiers() {
		return null;
	}

	@Override
	public void closeReader() throws IOException {

	}
}
