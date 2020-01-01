package com.framework.testbase;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.framework.elasticsearch.KibanaStatus;
import com.framework.restapi.RestExecutor;
import com.framework.restapi.RestValidator;
import com.framework.utils.AllureUtils;
import com.framework.utils.FileUtil;
import com.framework.utils.ProcessUtils;
import com.framework.utils.PropertyUtils;

/**
 * @author rampourw
 *
 */
public class ApiHooks {

	protected RestExecutor restExecutor;
	protected RestValidator restValidator;
	private ProcessUtils processUtils;
	private KibanaStatus kibanaStatus;

	public ApiHooks() throws Exception {
		restExecutor = new RestExecutor();
		restValidator = new RestValidator();
		processUtils = new ProcessUtils();
		kibanaStatus = new KibanaStatus();
	}

	/**
	 * Setting up the allure , log4j env and delete the file directories and elastic
	 * search data
	 * 
	 * @throws Exception
	 */
	@BeforeSuite
	public void setAllureEnvironment() throws Exception {
		new FileUtil().deleteDirectories();
		PropertyUtils.setLogger();
		processUtils.deleteElasticSearchData();
		processUtils.startKibanaServer();
		new AllureUtils().allureEnvironment();
		kibanaStatus.openKibanaServer();

	}

	/**
	 * refresh the kibana server to view the live test results
	 */
	@AfterMethod
	public void refreshKibanaServer() {
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
