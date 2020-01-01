package com.framework.utils;

import org.apache.log4j.Logger;
import io.qameta.allure.Step;

/**
 * @author rampourw
 *
 */
public class CustomLog {

	private static Logger log = Logger.getLogger(CustomLog.class.getName());

	private CustomLog() {
	}

	/**
	 * @param message -- info message
	 */
	@Step("{0}")
	public static void info(String message) {
		String strCallerClass = new Exception().getStackTrace()[1].toString();
		strCallerClass = strCallerClass.substring(strCallerClass.indexOf('(') + 1, strCallerClass.indexOf(')'))
				.replace(".java", "");
		log.info(strCallerClass + "\t-\t" + message);
	}

	/**
	 * @param message -- warning message
	 */
	@Step("{0}")
	public static void warn(String message) {
		log.warn(message);
	}

	/**
	 * @param message -- error message
	 */
	@Step("{0}")
	public static void error(String message) {
		log.error(message);
	}

	/**
	 * @param message -- fatal message
	 */
	@Step("{0}")
	public static void fatal(String message) {
		log.fatal(message);
	}

	/**
	 * @param message -- debug message
	 */
	public static void debug(String message) {
		log.debug(message);
	}

}
