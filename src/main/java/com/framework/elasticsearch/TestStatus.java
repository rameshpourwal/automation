package com.framework.elasticsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rampourw
 *
 */
public class TestStatus {

	@JsonProperty("testClass")
	private String testClass;

	@JsonProperty("description")
	private String description;

	@JsonProperty("status")
	private String status;

	@JsonProperty("executionTime")
	private String executionTime;

	/**
	 * @param description -- test description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param executionTime -- test execution time
	 */
	public void setExecutionDate(String executionTime) {
		this.executionTime = executionTime;
	}

	/**
	 * @param status -- test result status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param testClass -- test class name
	 */
	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}

}
