package io.happykraken.basic.reports.models;

public class TestRunFinished {
	private Boolean success;
	private Timestamp timestamp;

	public TestRunFinished() {
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
