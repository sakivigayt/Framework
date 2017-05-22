package com.framework.setup;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.applitools.eyes.Eyes;
import com.drivers.DriverConfig;
import com.drivers.DriverFactory;
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

	//protected Eyes eyes;
	

	@BeforeMethod
	public void setup(ITestContext test) {
  System.out.println("RUNNING TEST");
  DriverConfig config=new DriverConfig();
	config.setBrowserName(test.getCurrentXmlTest().getParameter("browser"));
	DriverFactory.initiateDriver();
	DriverFactory.setDriverConfig(config);
	/*	DriverFactory.initiateDriver();
		driver = DriverFactory.getDriver()*/;
	/*	eyes = new Eyes();
		eyes.setApiKey("1nSenw9MMwrjI3eaqNkNUspVj8nmmdobaW4GaHA106UJU110");*/
	}

	@AfterMethod
	public void tearDown() {
		
			DriverFactory.quitDriver();
		
	}

}
