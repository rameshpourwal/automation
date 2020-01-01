package com.framework.testbase;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.framework.base.PageBase;
import com.framework.base.TestBase;
import com.framework.elasticsearch.KibanaStatus;
import com.framework.manager.DriverManager;
import com.framework.manager.PageObjectManager;
import com.framework.utils.AllureUtils;
import com.framework.utils.CustomLog;
import com.framework.utils.FileUtil;
import com.framework.utils.ProcessUtils;
import com.framework.utils.PropertyUtils;

public class Hooks extends TestBase {

	protected PageObjectManager pageObjectManager;
	private ProcessUtils processUtils;
	private KibanaStatus kibanaStatus;
	

	public Hooks() throws Exception {
		pageObjectManager = new PageObjectManager();
		processUtils = new ProcessUtils();
		kibanaStatus = new KibanaStatus();
	}

	/**
	 * @throws Exception
	 */
	@BeforeSuite
	public void setAllureEnvironment() throws Exception {
		new FileUtil().deleteDirectories();
		PropertyUtils.setLogger();
		processUtils.deleteElasticSearchData();
		new AllureUtils().allureEnvironment();
		processUtils.startKibanaServer();
		kibanaStatus.openKibanaServer();
	}

	/**
	 * launch the web application
	 * 
	 * @param browserName
	 * @throws Exception
	 */
	@BeforeMethod
	@Parameters("browserName")
	public void tearUp(String browserName) throws Exception {
		openBrowser(browserName);
		DriverManager.getWebDriver().get(PropertyUtils.getConfigProperty("url"));
		maximizeBrowser();
		new PageBase().waitUntilPageIsReady();
	}

	/**
	 * quite from the web application
	 */
	@AfterMethod
	public void tearDown() {
		CustomLog.info("Close the driver");
		DriverManager.getWebDriver().quit();
		kibanaStatus.refreshKibanaServer();
	}

	/**
	 * showing the allure report
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@AfterSuite
	public void showAllureReport() throws IOException, InterruptedException {
		processUtils.showAllureReport();
		kibanaStatus.closeKibanaServer();
	}

}
