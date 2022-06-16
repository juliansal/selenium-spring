package io.happykraken.basic.reports.models;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class TestRunFinishedMsg {
	public TestRunFinished testRunFinished;

	public TestRunFinishedMsg() {
	}

	public TestRunFinished getTestRunFinished() {
		return testRunFinished;
	}

	public void setTestRunFinished(TestRunFinished testRunFinished) {
		this.testRunFinished = testRunFinished;
	}
}


