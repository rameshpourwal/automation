package com.framework.smoke;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.framework.screen.Welcome;
import com.framework.testbase.Hooks;

import io.qameta.allure.Description;

public class TestCase2 extends Hooks {
	private Welcome welcome;

	public TestCase2() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(description = "test demo 2")
	@Description("test description 2")
	public void test_2() throws InterruptedException {
		welcome = pageObjectManager.getWelcome();
		assertTrue(welcome.isLogoDisplay(), "Logo isn't displayed");

	}
}
