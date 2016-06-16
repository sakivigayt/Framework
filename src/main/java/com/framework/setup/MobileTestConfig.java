package com.framework.setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.drivers.DriverFactory;
import com.relevantcodes.extentreports.ExtentTest;

public class MobileTestConfig {

	/**
	 * The ExtentTest test
	 */
	protected ExtentTest test;

	/**
	 * The WebDriver driver
	 */
	protected AppiumDriver driver;

	
	@BeforeMethod
	public void setup(ITestContext test){
		DriverFactory.initiateDriver(test.getCurrentXmlTest()
				.getParameter("browser"));
		driver=DriverFactory.getAppiumDriver();
	}
	
	@AfterMethod
	public void tearDown(){
		DriverFactory.quitAppiumDriver();
	}
}
