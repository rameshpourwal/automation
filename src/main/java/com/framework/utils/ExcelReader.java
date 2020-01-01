package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author rampourw
 *
 */
public class ExcelReader {

	private FileInputStream inputStream = null;
	private HSSFWorkbook workbook = null;
	private HSSFSheet sheet = null;
	private String executionType = System.getProperty("executionType");

	/**
	 * ExcelReader constructor that will read the file specified as a fileLocation
	 * argument.
	 * 
	 * @param fileLocation -- file location
	 */
	public ExcelReader(String fileLocation) {
		try {
			inputStream = new FileInputStream(fileLocation);
			workbook = new HSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);
			inputStream.close();
		} catch (FileNotFoundException e) {

			CustomLog.warn(e.getMessage());
		} catch (IOException ioe) {
			CustomLog.warn(ioe.getMessage());
		}

	}

	/**
	 * Retrieve CaseToRun column value for given site name
	 * 
	 * @param sheetName  -- sheet name
	 * @param columnName -- sheet column name
	 * @param suiteName  -- suite name
	 * @return
	 */
	public String retriveToRunFlag(String sheetName, String columnName, String suiteName) {
		String suiteToRunValue = null;
		int sheetIndex = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(sheetIndex);

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {

			HSSFRow row = sheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(suiteName)) {
				suiteToRunValue = row.getCell(1).getStringCellValue();
				break;

			}
		}
		return suiteToRunValue;
	}

	/**
	 * Retrieve browser column value from first row of given sheet name.
	 * 
	 * @param SheetName -- file sheet name
	 * @return
	 */
	public String retrieveBrowserName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		HSSFRow row = sheet.getRow(0);
		return row.getCell(2).getStringCellValue();

	}

	/**
	 * Reads given excel sheet and returns 2 dimensional array containing in memory
	 * representation of test data
	 * 
	 * @param sheetName -- file name
	 * @return
	 */
	public Object[][] retrieveTestCaseData(String sheetName) {

		sheet = workbook.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		Object [][]object = new Object[rowNum][6];

		for (int i = 0; i < rowNum; i++) {
			HSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				object[i][j] = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();

			}
		}
		return object;
	}

	/**
	 * Method to fetch list of suites to be executed.
	 * 
	 * @return
	 */

	public List<String> getSelectedModuleNames() {
		List<String> allTestSuite = new ArrayList<>();

		try {
			if (executionType.equalsIgnoreCase("api")) {
				sheet = workbook.getSheet("api_automation_suites");
			} else {
				sheet = workbook.getSheet("web_automation_suites");
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				String strSuiteName = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
				String strSuiteToRun = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

				if (strSuiteToRun.equalsIgnoreCase("y") || strSuiteToRun.equalsIgnoreCase("yes"))
					allTestSuite.add(strSuiteName);

			}
		} catch (Exception e) {
			CustomLog.error("Some error occured while fetching list of selected modules. See below stack trace");
			CustomLog.error(e.getMessage());
		}

		return allTestSuite;
	}

	/**
	 * Method to fetch number of test cases(marked as 'Y' in excel file) to be
	 * executed for a particular suite.
	 * 
	 * @return
	 */
	public List<String> getSelectedTestCases() {
		List<String> allTestCases = new ArrayList<>();

		try {
			sheet = workbook.getSheet("TestCasesList");

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				String strSuiteName = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
				String strSuiteToRun = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

				if (strSuiteToRun.equalsIgnoreCase("y") || strSuiteToRun.equalsIgnoreCase("yes"))
					allTestCases.add(strSuiteName);

			}
		} catch (Exception e) {
			CustomLog.error("Some error occured while fetching list of selected Testcases. See below stack trace");
			CustomLog.error(e.getMessage());
		}

		return allTestCases;
	}

}
