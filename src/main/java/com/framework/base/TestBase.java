package com.framework.base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.framework.manager.DriverManager;
import com.framework.utils.CustomLog;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author rampourw
 *
 */
public class TestBase {

	/**
	 * Initialize the web driver
	 * 
	 * @param browserName -- browser name from the parameter
	 * @throws Exception
	 */
	public void openBrowser(String browserName) {
		WebDriver webDriver;
		CustomLog.info("browser set " + browserName);

		switch (browserName.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
			DriverManager.setWebDriver(webDriver);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			webDriver = new EdgeDriver();
			DriverManager.setWebDriver(webDriver);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
			DriverManager.setWebDriver(webDriver);
			break;

		}

	}

	/**
	 * maximize the web page
	 */
	public void maximizeBrowser() {
		DriverManager.getWebDriver().manage().window().maximize();
		DriverManager.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.getWebDriver().manage().deleteAllCookies();

	}

}
