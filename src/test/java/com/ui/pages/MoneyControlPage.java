package com.ui.pages;

import org.openqa.selenium.WebDriver;

import com.elements.Button;
import com.elements.Element;
import com.elements.Element.LocatorType;
import com.elements.Link;
import com.elements.TextField;
import com.framework.setup.BasePage;

/**
 * Elements of the pages are listed here
 * @author cbhaka
 */
public class MoneyControlPage extends BasePage {
	
	/**
	 * The webdriver
	 */
	
	
	
	/**
	 * The signIn element
	 */
	private Link signIn = new Link("Sign in", LocatorType.LINK_TEXT);
	
	/**
	 * The username element
	 */
	private TextField username = new TextField("signuname", LocatorType.ID);
	
	/**
	 * The password element 
	 */
	private TextField password = new TextField("signpwd", LocatorType.ID);
	
	/**
	 * The login element
	 */
	private Button login = new Button("//input[@value='sign in']", LocatorType.XPATH);
	
	/**
	 * The loggedInName element
	 */
	private Link loggedInName = new Link("usr_nm", LocatorType.ID);
	
	/**
	 * The companyName element
	 */
	private TextField companyName = new TextField("search_str", LocatorType.ID);
	
	/**
	 * The getQuote element
	 */
	private Button getQuote = new Button("//a[@title='Submit']", LocatorType.XPATH);
	
	/**
	 * The stockName element
	 */
	private Element stockName = new Element("//h1[text()='GAIL India']", LocatorType.XPATH);
	
	/**
	 * Constructor will set the driver
	 * @param driver 
	 */
	
	
	/**
	 * Login to the application
	 * @param user username 
	 * @param pass password 
	 */
	public void loginInApp(String user, String pass) {
		signIn.click();
		username.clearandType(user);
		password.clearandType(pass);
		login.click();
	}
	
	/**
	 * get the title of page
	 * @return the title
	 */
	public String getTitle() {
		return driver.getTitle();
	}
	
	/**
	 * Get the logged in name
	 * @return the logged in name
	 */
	public boolean getLoggedInName() {
		//loggedInName.getText();
		return loggedInName.isDisplayed();
	}
	
	/**
	 * Enter the company name
	 * @param name of company to enter 
	 */
	public void enterCompanyName(String name) {
		companyName.clearandType(name);
		getQuote.click();
	}
	
	/**
	 * Get the stock name
	 * @return the stock name
	 */
	public String getStockName() {
		return stockName.getText();
	}
}
