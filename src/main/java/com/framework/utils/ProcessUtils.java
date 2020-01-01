package com.framework.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author rampourw
 *
 */
public class ProcessUtils {

	/**
	 * @param command -- CLI command
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void runCommand(String command) throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("cmd.exe /c " + command + " ");
		process.waitFor(10, TimeUnit.SECONDS);
		process.destroyForcibly();
	}

	/**
	 * command start the allure server and showing report
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void showAllureReport() throws IOException, InterruptedException {
		runCommand("allure serve");
		CustomLog.info("Allure server started successfully");
	}

	/**
	 * methods delete the data from elastic search
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void deleteElasticSearchData() throws IOException, InterruptedException {
		String elasticSearchUrl = PropertyUtils.getConfigProperty("elasticsearchurl");
		runCommand("curl -XDELETE " + elasticSearchUrl + "");
		CustomLog.info("Elastic search data deleted successfully");
	}

	public void startKibanaServer() {
		try {
			String[] command = { "cmd.exe", "/C", "Start", "src\\test\\resources\\kibana\\bin\\kibana.bat" };
			Runtime.getRuntime().exec(command);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
