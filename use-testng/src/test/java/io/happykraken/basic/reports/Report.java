package io.happykraken.basic.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.log4testng.Logger;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


public class Report {
	private static final Logger logger = Logger.getLogger(Logger.class);
	private final List<Map<?, ?>> stepsMap = new ArrayList<>();
	private final ObjectMapper mapper = new ObjectMapper();
	IMessagesReader reader;

	public Report(IMessagesReader reader) {
		this.reader = reader;
	}

	public static void main(String[] args) {
		Report report = new Report(new MessagesReader());
		try {
			report.read();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void read() throws IOException, URISyntaxException {
		List<String> messages = reader.readMessagesLog();
		String testCaseMessage = reader.findTestCaseMessage(messages);
		Optional
				.ofNullable(testCaseMessage)
				.ifPresent(tCase -> reader.addToStepIdentifiersMap(tCase));

		for (Map<?, ?> stepStat : reader.getStepIdentifiers()) {
			String finished = reader.findStepFinishedMessage(messages, stepStat);
			String seconds = reader.findStepDuration(finished);
			String stepMsg = reader.findStepMessage(messages, stepStat);
			String stepName = reader.findStepName(stepMsg);

			stepsMap.add(Map.of(
					"step", stepName,
					"duration", Map.of("seconds", seconds)
			));
		}

		System.out.println("StepStats: " + mapper.writeValueAsString(reader.getStepIdentifiers()));
		System.out.println("StepsMap: " + mapper.writeValueAsString(stepsMap));
	}

}
