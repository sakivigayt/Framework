package com.reporting;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import com.drivers.DriverConfig;
import com.drivers.DriverFactory;
import com.log.LoggerFactory;
import com.log.MyLogger;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.TimestampUtils;

public class ReportingListener implements IResultListener , ITestListener {

	final static MyLogger logger = LoggerFactory.getLogger(ReportingListener.class);
	public static String reportLocation = null;

	/**
	 * This method is called at test start.
	 */
	public void onTestStart(ITestResult result) {

		ReportTestManager.startTest(result.getMethod().getMethodName());
		ReportTestManager.getTest().assignCategory(
				result.getMethod().getRealClass().getSimpleName()/*,
				getBrowserName()*/);

	}

	/**
	 * This method is called on test success
	 */
	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " Passed");
		if (!(ReportTestManager.getTest().getRunStatus() == LogStatus.FAIL)) {
			ReportUtil.logPass(result.getMethod().getMethodName() + " Passes");
			ReportTestManager.endTest();
			ReportManager.getReporter().flush();
		} else {
			ReportTestManager.endTest();
			ReportManager.getReporter().flush();
		}

	}

	/**
	 * This method is called on test failure
	 */
	public void onTestFailure(ITestResult result) {

		if (result.getThrowable() != null) {
			logger.error(result.getMethod()
					+ result.getThrowable().getMessage());
			ReportUtil.logFail("Test Case Fails with following message:: "
					+ result.getThrowable().getMessage());
			// Assert.assertTrue(false);
		}
		ReportTestManager.endTest();
		ReportManager.getReporter().flush();

	}

	/**
	 * This method is called when a test is skipped
	 */
	public void onTestSkipped(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + "SKIPPED");
		ReportUtil.logSkipped("Test Case Skipped");
		ReportTestManager.endTest();
		ReportManager.getReporter().flush();

	}

	/**
	 * This method is called when a test case fails but with certain success
	 * percentage
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method is called on start of test start
	 */
	public void onStart(ITestContext context) {
		try {
						
			
			String ReporterName;

			reportLocation = System.getProperty("user.dir") + "\\Reports\\"
					+ TimestampUtils.getTimeStamp() + "\\";
			ReporterName = context.getCurrentXmlTest().getName() + ".html";
			System.out.println(reportLocation + ReporterName);

			ReportManager.getReporter(reportLocation + ReporterName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is called when a test finishes
	 */
	public void onFinish(ITestContext context) {
		System.out.println("Test Case ends");
	}

	/**
	 * This method is called on configuration success
	 */
	public void onConfigurationSuccess(ITestResult itr) {

	}

	/**
	 * This method is called on configuration failure
	 */
	public void onConfigurationFailure(ITestResult itr) {

	}

	/**
	 * This method is called in configuration skip
	 */
	public void onConfigurationSkip(ITestResult itr) {

	}

	public String getBrowserName() {
		String browserName = null;

		Capabilities cap = ((RemoteWebDriver) DriverFactory.getDriver())
				.getCapabilities();
		browserName = cap.getBrowserName();

		return browserName;
	}

}
