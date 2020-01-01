package com.framework.smoke;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

import com.framework.customexception.AutomationException;
import com.framework.screen.Welcome;
import com.framework.testbase.Hooks;
import com.framework.utils.CustomLog;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class TestCase1 extends Hooks {

	private Welcome welcome;

	public TestCase1() throws Exception {
		super();
	}

	@Test(description = "test demo 1")
	@Description(" test description 1")
	@Severity(SeverityLevel.MINOR)
	@Issue("AM-112")

	public void test_1() throws AutomationException {
		welcome = pageObjectManager.getWelcome();
		assertFalse(welcome.isLogoDisplay(), "Logo  displayed");
		CustomLog.info("logo verified");

		welcome.clickOnhamburgerMenu();
		CustomLog.info("humburger section verified");

	}

}
