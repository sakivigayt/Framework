package com.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.exception.InitializationException;
import com.utils.PropReader;

/**
 * Enum for generating Specific Driver Type.
 */
public enum DriverType implements IDriverSetup {

	FIREFOX {

		public WebDriver getDriverObject(DesiredCapabilities dc) {

			try {
				PropReader prop = new PropReader(System.getProperty("user.dir")
						+ File.separator + "config.properties");
				if (prop.getValue("RemoteExecution").equals("Yes")) {
					try {
						return new RemoteWebDriver(new URL(
								prop.getValue("RemoteDriver")), dc);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						throw new InitializationException(
								"URL is not correct for remote webdriver"
										+ e.getMessage());
					}
				}
				return new FirefoxDriver(dc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new InitializationException("Firefox not initialized"
						+ e.getMessage());
			}

		}

		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			System.out.println(config.getExplicitWaitTimeout());
			return capabilities;
		}

		@Override
		public AppiumDriver getAppiumDriverObject(DesiredCapabilities dc) {
			// TODO Auto-generated method stub
			return null;
		}

	},
	CHROME {

		public WebDriver getDriverObject(DesiredCapabilities dc) {
			try {
				System.setProperty("webdriver.chrome.driver",
						"src\\main\\resources\\chromedriver.exe");
				PropReader prop = new PropReader(System.getProperty("user.dir")
						+ File.separator + "config.properties");
				if (prop.getValue("RemoteExecution").equals("Yes")) {
					try {
						return new RemoteWebDriver(new URL(
								prop.getValue("RemoteDriver")), dc);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						throw new InitializationException(
								"URL is not correct for remote webdriver");
					}
				} else {

					return new ChromeDriver(dc);
				}
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block

				throw new InitializationException("Webdriver not initialized"
						+ e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Initialization error displayed");
				throw new InitializationException(
						"	Chrome Driver not initialized" + e.getMessage());
			}

		}

		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			System.out.println(config.getExplicitWaitTimeout());
			return capabilities;
		}

		@Override
		public AppiumDriver getAppiumDriverObject(DesiredCapabilities dc) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	IE {

		public WebDriver getDriverObject(DesiredCapabilities dc) {
			try {
				System.setProperty("webdriver.ie.driver",
						"src\\main\\resources\\IEDriverServer.exe");
				PropReader prop = new PropReader(System.getProperty("user.dir")
						+ File.separator + "config.properties");
				if (prop.getValue("RemoteExecution").equals("Yes")) {
					try {
						return new RemoteWebDriver(new URL(
								prop.getValue("RemoteDriver")), dc);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						throw new InitializationException(
								"URL is not correct for remote webdriver");
					}
				} else {

					return new InternetExplorerDriver(dc);
				}
			} catch (WebDriverException e) {
				// TODO Auto-generated catch block

				throw new InitializationException("IE Driver not initialized"
						+ e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block

				throw new InitializationException("	IE Driver not initialized"
						+ e.getMessage());
			}

		}

		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {
			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			return capabilities;
		}

		@Override
		public AppiumDriver getAppiumDriverObject(DesiredCapabilities dc) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	APPIUM {

		@Override
		public WebDriver getDriverObject(DesiredCapabilities dc) {
			// TODO Auto-generated method stub
			return null;
		}
		

		@Override
		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {
			PropReader pr = new PropReader("MobileDevice.properties");
			
			/*File appDir = new File(pr.getValue("appdir"));
			File app = new File(appDir, pr.getValue("app"));*/
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("BROWSER_NAME", pr.getValue("browsername"));
			capabilities.setCapability("VERSION", pr.getValue("version"));
			capabilities.setCapability("deviceName", pr.getValue("devicename"));
			capabilities.setCapability("platformName", pr.getValue("platformname"));

			/*capabilities.setCapability("app", app.getAbsolutePath());*/
			
			capabilities.setCapability("appPackage", "com.android.calculator2");
			capabilities.setCapability("appActivity","com.android.calculator2.Calculator");

			return capabilities;
		}

		@Override
		public AppiumDriver getAppiumDriverObject(DesiredCapabilities dc) {
			AppiumDriver appiumDriver = null;
			try {
				appiumDriver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
						dc);
				appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return appiumDriver;
		}
		
	},

}
