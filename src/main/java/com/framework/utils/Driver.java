package com.framework.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

/**
 * @author rampourw
 *
 */
public class Driver {

	private static String strTestSuites;

	public static void main(String[] args) throws Exception {

		CustomLog.info("---------------Preparing For Execution-------------");

		String strBrowsers = System.getProperty("browsers");
		String strParallel = System.getProperty("parallel");
		String executionType = System.getProperty("executionType");

		CustomLog.info(strTestSuites + "--" + strBrowsers);

		List<String> selectedModules;
		if (strTestSuites != null) {
			selectedModules = Arrays.asList(strTestSuites.split(","));
		} else {
			ExcelReader excelConroller = new ExcelReader(PropertyUtils.getResourcePath().concat("testdata")
					.concat(File.separator).concat("TestCase.xls"));
			selectedModules = excelConroller.getSelectedModuleNames();
		}

		List<XmlSuite> suites = new ArrayList<>();

		XmlSuite browserSuite = new XmlSuite();

		List<String> listeners = new ArrayList<>();
		listeners.add("com.framework.elasticsearch.ExecutionListener");
		browserSuite.setListeners(listeners);

		browserSuite.setName("Test Suite - framework");
		if (strParallel.equalsIgnoreCase("true")) {
			browserSuite.setParallel(ParallelMode.TESTS);
			browserSuite.setThreadCount(2);
		}

		List<XmlClass> classes = new ArrayList<>();
		for (String ts : selectedModules) {

			XmlClass aClass = new XmlClass();
			aClass.setName(ts);
			classes.add(aClass);

		}

		if (executionType.equalsIgnoreCase("api")) {
			XmlTest test = new XmlTest(browserSuite);
			test.setName("api_Test ");
			test.setClasses(classes);
			suites.add(browserSuite);
		} else {
			String[] allBrowser = strBrowsers.split(",");
			for (String strBrowser : allBrowser) {
				HashMap<String, String> suiteParameters = new HashMap<>();
				suiteParameters.put("browserName", strBrowser);
				XmlTest test = new XmlTest(browserSuite);
				test.setName(strBrowser + "_Test ");
				test.setParameters(suiteParameters);
				test.setClasses(classes);
				suites.add(browserSuite);

			}
		}

		CustomLog.debug(browserSuite.toXml());

		try (FileWriter writer = new FileWriter(new File("MasterSuite.xml"))) {
			writer.write(browserSuite.toXml());
			writer.flush();

		} catch (IOException e) {
			CustomLog.warn("file not found " + e.getMessage());
		}

		CustomLog.debug(new File("MasterSuite.xml").getAbsolutePath());
		/*
		 * testng.setXmlSuites(Arrays.asList(browserSuite)); testng.run();
		 */

	}

}
