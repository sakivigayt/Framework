package com.impl;

import org.testng.Assert;
import com.drivers.DriverFactory;
import com.ui.pages.MoneyControlPage;


/**
 * Implementation at UI level
 * @author cbhaka
 */
public class MoneyControlImpl {
	
	/**
	 * Instance of MoneyControlPage
	 */
	MoneyControlPage moneyControlPage = new MoneyControlPage(DriverFactory.getDriver());
	
	/**
	 * Navigate to the application
	 */
	public void navigateToApp() {
		DriverFactory.getDriver().get("http://www.moneycontrol.com");
	}
	
	/**
	 * Login to the application
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		
		moneyControlPage.loginInApp(username, password);
	}
	
	/**
	 * Assertion of successful login
	 */
	public void assertSuccessfullLogin() {
		Assert.assertTrue(moneyControlPage.getLoggedInName());;
		
	}
	
	/**
	 * Get the quote 
	 * @param name
	 */
	public void getQuote(String name) {
		moneyControlPage.enterCompanyName(name);
	}
	
	/**
	 * Assert stock name
	 * @param stockName the stockName to assert
	 */
	public void assertStockName(String stockName) {
		String name = moneyControlPage.getStockName();
		Assert.assertEquals(name, stockName);
	}

}
