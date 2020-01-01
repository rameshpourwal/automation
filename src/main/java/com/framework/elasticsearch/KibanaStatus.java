package com.framework.elasticsearch;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.framework.utils.CustomLog;
import com.framework.utils.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author rampourw
 *
 */
public class KibanaStatus {

	private static WebDriver webDriver;

	/**
	 * open the kibana server on chrome for viewing the dashboard
	 * 
	 * @throws Exception
	 */
	public synchronized void openKibanaServer() throws IOException {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		maximizeBrowser();
		webDriver.get(PropertyUtils.getConfigProperty("kibanadashboard"));
	}

	/**
	 * maximize the browser
	 */
	private void maximizeBrowser() {
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().deleteAllCookies();

	}

	/**
	 * close the kibana web page
	 */
	public void closeKibanaServer() {
		try {
			webDriver.close();

		} catch (Exception e) {
			webDriver.quit();
		}

	}

	/**
	 * refresh the kibana page after every test case complete
	 */
	public void refreshKibanaServer() {
		webDriver.navigate().refresh();
		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			CustomLog.warn("Interrupted!" + e.getMessage());
			Thread.currentThread().interrupt();

		}
	}

}
