package com.framework.manager;

import com.framework.screen.Welcome;

/**
 * @author rampourw
 *
 */
public class PageObjectManager {

	private Welcome welcome;

	public Welcome getWelcome() {
		return (welcome == null) ? new Welcome() : welcome;
	}

}
