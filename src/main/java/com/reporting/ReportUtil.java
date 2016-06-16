package com.reporting;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;

import com.drivers.DriverFactory;
import com.log.LoggerFactory;
import com.log.MyLogger;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.PropReader;

public class ReportUtil {

	/**
	 * The Logger logger
	 */
	private final static MyLogger logger = LoggerFactory
			.getLogger(ReportUtil.class);

	/**
	 * This method is called whenever a test case passes
	 * 
	 * @param detail
	 *            the detial for passed test case
	 */
	public static void logPass(String detail) {
		logger.info("Test Case passes" + ReportTestManager.getTest().toString());
		Assert.assertTrue(true, detail);
		ReportTestManager.getTest().log(LogStatus.PASS, detail);
		// ReportTestManager.getTest().addScreenCapture(captureScreenShot());
	}

	/**
	 * This method is called whenever a test case fails
	 * 
	 * @param detail
	 *            the detail for failed test case
	 */
	public static void logFail(String detail) {
		failure(detail);
	}

	public static void verifyEqual(Object actual, Object expected) {
		if (!actual.equals(expected)) {
			logFail(actual + " not equals to " + expected);
		} else {
			logPass(actual + " equals to " + expected);
		}

	}

	/**
	 * This method is called whenever a test case is skipped
	 * 
	 * @param detail
	 *            the details for skipped test case
	 */
	public static void logSkipped(String detail) {
		logger.info("Test Case skipped");
		ReportTestManager.getTest().log(LogStatus.SKIP, detail);
		// ReportTestManager.getTest().
	}

	/**
	 * This method is called to check if the element is displayed or not
	 * 
	 * @param by
	 *            reference element
	 */
	public static void isElementDisplayed(By by) {
		logger.info("Inside isElementDisplayed");
		WebDriver driver = DriverFactory.getDriver();

		if (driver.findElement(by).isDisplayed()) {
			logPass("Element Displayed Successfully");
		} else {
			logFail("Element not displayed");
		}
	}

	/**
	 * This method is called to check if any element is selected or not
	 * 
	 * @param by
	 *            reference element
	 */
	public static void isElementSelected(By by) {
		logger.info("Inside isElementSelected");
		WebDriver driver = DriverFactory.getDriver();

		if (driver.findElement(by).isSelected()) {
			logPass("Element is selected");
		} else {
			logFail("Element is not selected");
		}
	}

	/**
	 * This method is called to check if the element is enabled or not.
	 * 
	 * @param by
	 *            reference element
	 */
	public static void isElementEnabled(By by) {
		logger.info("Inside isElementEnabled");
		WebDriver driver = DriverFactory.getDriver();
		if (driver.findElement(by).isEnabled()) {
			logPass("Element is Enabled");
		} else {
			logFail("Element is not enabled");
		}
	}

	/**
	 * capture the screenshot
	 * 
	 * @return the file of capture screenshot.
	 */

	public static void assertEqual(String actual, String expected) {

		if (actual.equals(expected)) {
			logPass(actual + " is equals to " + expected);

		} else {
			Assert.assertEquals(actual, expected);
		}
	}

	public static void assertTrue(boolean flag) {

		if (flag) {
			logPass("Assertion Passes");
		} else {
			logFail("Assertion Fails, expected True but was false");
		}
	}

	public static void assertFalse(boolean flag) {

		if (flag) {
			logFail("Assertion Fails");
		} else {
			logPass("Assertion Passes");
		}
	}

	public static void assertTrue(boolean flag, String details) {

		if (flag) {
			logPass(details);
		} else {
			logFail(details);
		}
	}

	public static void assertFalse(boolean flag, String details) {

		if (flag) {
			logFail(details);
		} else {
			logPass(details);
		}
	}

	public static void logInfo(String detail) {

		ReportTestManager.getTest().log(LogStatus.INFO, detail);

	}

	private static void failure(String detail) {
		try {
			logger.info("Inside Failure");
			ReportTestManager.getTest().log(
					LogStatus.FAIL,
					detail,
					ReportTestManager.getTest().addScreenCapture(
							captureScreenShot()));
			// Assert.assertTrue(false);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			ReportTestManager.getTest().log(LogStatus.FAIL, detail);
		}

	}

	private static String captureScreenShot() {
		String file = null;
		PropReader pr = new PropReader("Config.properties");
		if (pr.getValue("RemoteExecution").equalsIgnoreCase("yes")) {
			Augmenter augmenter = new Augmenter();
			TakesScreenshot ts = (TakesScreenshot) augmenter
					.augment(DriverFactory.getDriver());
			File sr = ts.getScreenshotAs(OutputType.FILE);
			file = ReportingListener.reportLocation + "\\SnapShots\\"
					+ System.currentTimeMillis() + ".jpeg";
			try {
				FileUtils.copyFile(sr, new File(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return file;
		} else {
			File src = ((TakesScreenshot) DriverFactory.getDriver())
					.getScreenshotAs(OutputType.FILE);

			// now copy the screenshot to desired location using copyFile method
			file = ReportingListener.reportLocation + "\\SnapShots\\"
					+ System.currentTimeMillis() + ".jpeg";
			try {
				FileUtils.copyFile(src, new File(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info("Screenshot not taken", e);
			}
		}
		logger.info("Screenshot taken at following location ::" + file);
		return file;

	}

}
