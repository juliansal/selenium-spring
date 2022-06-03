package io.happykraken.basic.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.log4testng.Logger;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;


public class Report {
	private static final Logger logger = Logger.getLogger(Logger.class);
	private final List<Map<?, ?>> stepMap = new ArrayList<>();
	private final ObjectMapper mapper = new ObjectMapper();
	IMessagesReader messagesReportReader;

	public Report() {
		this.messagesReportReader = new MessagesReader();
	}

	public static void main(String[] args) {
		Report reader = new Report();
		try {
			reader.read();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void read() throws IOException, URISyntaxException {
		List<String> messages = messagesReportReader.readMessagesLog();
		String testCaseMessage = messagesReportReader.findTestCaseMessage(messages);
		Optional
				.ofNullable(testCaseMessage)
				.ifPresent(tCase -> messagesReportReader.addToStepIdentifiersMap(tCase));

		for (Map<?, ?> stepStat : messagesReportReader.getStepIdentifiers()) {
			String finished = messagesReportReader.findStepFinishedMessage(messages, stepStat);
			String seconds = messagesReportReader.findStepDuration(finished);
			String stepMsg = messagesReportReader.findStepMessage(messages, stepStat);
			String stepName = messagesReportReader.findStepName(stepMsg);

			stepMap.add(Map.of(
					"step", stepName,
					"duration", Map.of("seconds", seconds)
			));
		}

		System.out.println("altStepStats: " + mapper.writeValueAsString(messagesReportReader.getStepIdentifiers()));
		System.out.println("altStepMap: " + mapper.writeValueAsString(stepMap));
	}

}
