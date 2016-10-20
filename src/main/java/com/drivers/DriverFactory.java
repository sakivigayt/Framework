package com.drivers;

import org.openqa.selenium.WebDriver;

import com.log.LoggerFactory;
import com.log.MyLogger;

public class DriverFactory {

	private static ThreadLocal<DriverClass> driverThread = new ThreadLocal<DriverClass>();

	private final static MyLogger logger = LoggerFactory
			.getLogger(DriverFactory.class);

	public static void initiateDriver(final String browser) {

		if (driverThread.get() == null) {
			logger.info("Initializing Driver");
			driverThread.set(new DriverClass(browser.toUpperCase()));
		} else {
			driverThread.set(driverThread.get());
		}

	};

	public static synchronized WebDriver getDriver() {

		if (driverThread.get().getDriver() == null) {
			initiateDriver("firefox");
		}

		return driverThread.get().getDriver();
	}

	public static synchronized void quitDriver() {
		if (!((driverThread.get().getDriver()) == null)) {
			driverThread.get().getDriver().quit();
		}
		driverThread.remove();
	}

	public static DriverConfig getConfig() {
		return driverThread.get().getConfig();
	}

}
