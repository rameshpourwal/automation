package com.framework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author rampourw
 *
 */
public class FileUtil {

	/**
	 * @param folderName -- folder name
	 * @throws IOException
	 */
	public void deleteDirectory(String folderName) throws IOException {
		String directory = System.getProperty("user.dir").concat(File.separator).concat(folderName);
		FileUtils.deleteDirectory(new File(directory));
		CustomLog.info("Folder deleted " + folderName);
	}

	/**
	 * @param directory  -- folder directory name
	 * @param folderName -- folder name
	 * @throws IOException
	 */
	public void deleteDirectory(String directory, String folderName) throws IOException {
		FileUtils.deleteDirectory(new File(directory.concat(File.separator).concat(folderName)));
	}

	public void deleteDirectories() throws IOException {
		deleteDirectory("allure-results");
		deleteDirectory("Log4j");
	}
}
