package com.framework.setup;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.drivers.DriverFactory;
import com.log.LoggerFactory;
import com.log.MyLogger;
import com.relevantcodes.extentreports.ExtentTest;

public class WebTest {

	/**
	 * The ExtentTest test
	 */
	protected ExtentTest test;

	/**
	 * The WebDriver driver
	 */
	protected WebDriver driver;

	/**
	 * The Logger logger
	 */
	private static final MyLogger logger = LoggerFactory
			.getLogger(WebTest.class);

	
	@BeforeMethod
	public void setup(ITestContext test){
		DriverFactory.initiateDriver(test.getCurrentXmlTest()
				.getParameter("browser"));
		DriverFactory.getDriver();
	}
	
	@AfterMethod
	public void tearDown(){
		DriverFactory.quitDriver();
	}

}
