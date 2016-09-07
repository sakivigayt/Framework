package com.drivers;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.exception.InitializationException;
import com.utils.JsonReader;
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
			capabilities.setBrowserName(JsonReader.setCapability("firefox", "browserName"));
			System.out.println(config.getExplicitWaitTimeout());
			return capabilities;
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
			capabilities.setPlatform(Platform.WINDOWS);
			capabilities.setBrowserName(BrowserType.IE);
			capabilities.setVersion(JsonReader.setCapability("ie", "browserVersion"));
			
			return capabilities;
		}

	},
	
	ANDROID {

		@Override
		public WebDriver getDriverObject(DesiredCapabilities dc) {
			WebDriver driver = null;
			
			try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
						dc);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return driver;
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
			capabilities.setCapability("browserName", "Chrome");
			
			//capabilities.setCapability("app", app.getAbsolutePath());
			
		/*	capabilities.setCapability("appPackage", "com.android.calculator2");
			capabilities.setCapability("appActivity","com.android.calculator2.Calculator");*/

			return capabilities;
		}
		
	},

}
