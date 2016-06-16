package com.reporting;

import java.util.HashMap;
import java.util.Map;

import com.log.LoggerFactory;
import com.log.MyLogger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ReportTestManager {

	/**
	 * The Logger logger
	 */
	final static MyLogger logger = LoggerFactory
			.getLogger(ReportTestManager.class);

	/**
	 * Collection to store ExtentTest
	 */
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	/**
	 * The ExtentReports reference extent
	 */
	private static ExtentReports extent = ReportManager.getReporter();

	/**
	 * This method returns the ExtentTest reference
	 * 
	 * @return ExtentTest
	 */
	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	/**
	 * This method Ends and prepares the test to be added to the report on
	 * flush()
	 */
	public static synchronized void endTest() {
		extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread()
				.getId())));
	}

	/**
	 * This method starts and prepared the test to be added to report.
	 * 
	 * @param testName
	 *            the name of test
	 * @return
	 */
	public static synchronized ExtentTest startTest(String testName) {
		
		return startTest(testName, "");
	}

	/**
	 * This method starts the test with name and desc
	 * 
	 * @param testName
	 *            the name of test
	 * @param desc
	 *            the desc of test
	 * @return
	 */
	public static synchronized ExtentTest startTest(String testName, String desc) {
		
		ExtentTest test = extent.startTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

		return test;
	}
}