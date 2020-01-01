package com.framework.screen_locator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

/**
 * @author rampourw
 *
 */
public class WelcomeLocator {

	@CacheLookup
	@FindBy(className  = "nav-logo-lin")
	public WebElement logo;

	@CacheLookup
	@FindBy(className = "nav-search-field ")
	public WebElement searchField;

	@CacheLookup
	@FindBy(id = "nav-hamburger-menu")
	public WebElement hamburgerMenu;
}
