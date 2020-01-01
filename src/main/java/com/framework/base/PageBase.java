package com.framework.base;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.framework.customexception.AutomationException;
import com.framework.manager.DriverManager;
import com.framework.utils.CaptureScreenShot;
import com.framework.utils.CustomLog;

/**
 * @author rampourw
 *
 */

public class PageBase {

	private FluentWait<WebDriver> waitDriver;
	private CaptureScreenShot captureScreenShot;

	public PageBase() {
		captureScreenShot = new CaptureScreenShot();
	}

	/**
	 * wait until the web page completely loaded
	 */
	public void waitUntilPageIsReady() {
		CustomLog.info("Wait until the page load");
		waitDriver = new FluentWait<>(DriverManager.getWebDriver());

		ExpectedCondition<Boolean> pageLoadCondition = (driver -> (((JavascriptExecutor) DriverManager.getWebDriver())
				.executeScript("return document.readyState").equals("complete")));
		waitDriver.until(pageLoadCondition);
	}

	/**
	 * waiting and verify the web element is displayed
	 * 
	 * @param element       -- web element
	 * @param iTimeOutInSec -- time out in seconds
	 * @return
	 */
	public boolean isWebElementDisplayed(WebElement element, int iTimeOutInSec) {
		try {
			waitDriver = new FluentWait<>(DriverManager.getWebDriver());
			waitDriver.withMessage("Finding Element").pollingEvery(Duration.ofMillis(500))
					.withTimeout(Duration.ofSeconds(iTimeOutInSec)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOf(element));
			heighlightElement(element);
			return true;

		} catch (Exception e) {
			CustomLog.error(" Element Not found: " + e.getMessage());
			captureScreenShot.getScreenShot();
			return false;
		}
	}

	/**
	 * @param element -- web element
	 * @return
	 */
	public boolean isWebElementDisplayed(WebElement element) {
		return isWebElementDisplayed(element, 60);
	}

	/**
	 * highlight the web element
	 * 
	 * @param element -- web element
	 */
	private void heighlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: white; border: 5px solid green;');", element);
	}

	/**
	 * verify the element is clickbale and click on the element
	 * 
	 * @param webElement    -- web element
	 * @param iTimeOutInSec -- time out in seconds
	 * @throws Exception
	 */
	public void customClick(WebElement webElement, int iTimeOutInSec) throws AutomationException {
		if (isWebElementClickable(webElement, iTimeOutInSec)) {
			webElement.click();
		} else {
			captureScreenShot.getScreenShot();
			throw new AutomationException("Elemtent not clickable ");
		}
	}

	/**
	 * verify the element is clickable
	 * 
	 * @param element       -- web element
	 * @param iTimeOutInSec -- time out in seconds
	 * @return
	 */
	private boolean isWebElementClickable(WebElement element, int iTimeOutInSec) {
		try {
			waitDriver = new FluentWait<>(DriverManager.getWebDriver());
			waitDriver.withMessage("Finding Element").pollingEvery(Duration.ofMillis(500))
					.withTimeout(Duration.ofSeconds(iTimeOutInSec)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
			heighlightElement(element);
			return true;

		} catch (Exception e) {
			CustomLog.error(" Element Not clickable: " + e.getMessage());
			return false;
		}
	}
}
