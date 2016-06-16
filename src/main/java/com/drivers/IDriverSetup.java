package com.drivers;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface IDriverSetup {
	
	WebDriver getDriverObject (DesiredCapabilities dc);
	
	DesiredCapabilities getDesiredCapabilities(DriverConfig config);
	
	AppiumDriver getAppiumDriverObject(DesiredCapabilities dc);

}
