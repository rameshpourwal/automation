package com.framework.screen;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.framework.base.PageBase;
import com.framework.customexception.AutomationException;
import com.framework.manager.DriverManager;
import com.framework.screen_locator.WelcomeLocator;
import com.framework.utils.CustomLog;

/**
 * @author rampourw
 *
 */
public class Welcome extends PageBase {

	private WelcomeLocator welcomeLocator;

	public Welcome() {
		welcomeLocator = new WelcomeLocator();
		PageFactory.initElements(DriverManager.getWebDriver(), welcomeLocator);
	}

	public boolean isLogoDisplay() {
		boolean status = true;
		CustomLog.info("verify logo is present ");
		WebElement menuElement = welcomeLocator.logo;
		status = isWebElementDisplayed(menuElement);

		return status;
	}

	public void clickOnhamburgerMenu() throws AutomationException {
		CustomLog.info("click on the humburger button");
		customClick(welcomeLocator.hamburgerMenu, 15);
	}
}
