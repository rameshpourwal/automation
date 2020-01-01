package com.framework.utils;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import java.io.File;
import java.io.IOException;
import com.google.common.collect.ImmutableMap;

/**
 * @author rampourw
 *
 */
public class AllureUtils {

	private String executionType;

	public AllureUtils() {
		executionType = System.getProperty("executionType");
	}

	public void allureEnvironment() throws IOException {

		allureEnvironmentWriter(
				ImmutableMap.<String, String>builder().put("Browser", getBrowser()).put("URL", getUrl()).build(),
				System.getProperty("user.dir").concat(File.separator).concat(getAllureReportDirectory())
						.concat(File.separator));

	}

	private String getUrl() throws IOException {
		String url = "";
		if (executionType.equalsIgnoreCase("api")) {
			url = PropertyUtils.getConfigProperty("apiurl");
		} else {
			url = PropertyUtils.getConfigProperty("url");
		}
		return url;

	}

	private String getBrowser() {
		String browsers = "";
		if (executionType.equalsIgnoreCase("api")) {
			browsers = executionType;
		} else {
			browsers = System.getProperty("browsers");
		}
		return browsers;

	}

	private String getAllureReportDirectory() throws IOException {

		return PropertyUtils.getProperty("allure.properties", "allure.results.directory");
	}

}
