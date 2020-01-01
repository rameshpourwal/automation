package com.framework.elasticsearch;

import java.time.LocalDateTime;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author rampourw
 *
 */
public class ExecutionListener implements ITestListener {

	private TestStatus testStatus;

	public void onTestStart(ITestResult iTestResult) {
		this.testStatus = new TestStatus();
	}

	public void onTestSuccess(ITestResult iTestResult) {
		this.sendStatus(iTestResult, "PASS");
	}

	public void onTestFailure(ITestResult iTestResult) {
		this.sendStatus(iTestResult, "FAIL");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		this.sendStatus(iTestResult, "SKIPPED");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// skip
	}

	public void onStart(ITestContext iTestContext) {
		// skip
	}

	public void onFinish(ITestContext iTestContext) {
		// skip
	}

	/**
	 * set the status of the executed test case and send the result on elastic
	 * search
	 * 
	 * @param iTestResult -- passing the test result of test-ng
	 * @param status      -- passing the status of test result
	 */
	private void sendStatus(ITestResult iTestResult, String status) {
		this.testStatus.setTestClass(iTestResult.getTestClass().getName());
		this.testStatus.setDescription(iTestResult.getMethod().getDescription());
		this.testStatus.setStatus(status);
		this.testStatus.setExecutionDate(LocalDateTime.now().toString());
		ResultSender.send(this.testStatus);
	}

}