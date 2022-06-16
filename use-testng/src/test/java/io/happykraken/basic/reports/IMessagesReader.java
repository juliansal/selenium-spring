package io.happykraken.basic.reports;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface IMessagesReader {
	List<String> readMessagesLog() throws URISyntaxException, IOException;

	void addToStepIdentifiersMap(String tCase);

	String findStepName(String line);

	String findStepDuration(String finishedTestStep);

	String findTestCaseMessage(List<String> messages);

	String findStepFinishedMessage(List<String> messages, Map<?, ?> stepStats);

	String findStepMessage(List<String> messages, Map<?, ?> stepStats);

	List<Map<?, ?>> getStepIdentifiers();

	void closeReader() throws IOException;
}
