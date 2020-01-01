package com.framework.manager;

import org.openqa.selenium.WebDriver;

/**
 * @author rampourw
 *
 */
public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private DriverManager() {

	}

	/**
	 * return the web driver instance
	 * 
	 * @return
	 */
	public static WebDriver getWebDriver() {
		return driver.get();
	}

	/**
	 * set the webdriver instance
	 * 
	 * @param webDriver -- web driver instance
	 */
	public static void setWebDriver(WebDriver webDriver) {
		driver.set(webDriver);
	}

}
