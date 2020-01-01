package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author rampourw
 *
 */
public class PropertyUtils {

	private PropertyUtils() {

	}

	/**
	 * return the property value based on the key value
	 * 
	 * @param fileName -- file name
	 * @param key      -- key name
	 * @return
	 * @throws IOException
	 */
	public static String getProperty(String fileName, String key) throws IOException {
		Properties properties = new Properties();
		String filePath = getResourcePath().concat(fileName);
		properties.load(new FileInputStream(new File(filePath)));
		return properties.getProperty(key);
	}

	/**
	 * return the resources directory path
	 * 
	 * @return
	 */
	public static String getResourcePath() {
		return System.getProperty("user.dir").concat(File.separator).concat("src").concat(File.separator).concat("test")
				.concat(File.separator).concat("resources").concat(File.separator);
	}

	/**
	 * return the value from configuration property file
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getConfigProperty(String key) throws IOException {
		String fileName = "config".concat(File.separator).concat("configuration.properties");
		return getProperty(fileName, key);
	}

	/**
	 * setting up the log4j property
	 */
	public static void setLogger() {
		String log4jConfPath = getResourcePath().concat("config").concat(File.separator).concat("log4j.properties");
		PropertyConfigurator.configure(log4jConfPath);
	}

}
