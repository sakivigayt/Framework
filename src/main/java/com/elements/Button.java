package com.elements;

import org.openqa.selenium.By;

import com.log.LoggerFactory;
import com.log.MyLogger;

/**
 * Button Specific implementation for WebElements
 * 
 */
public class Button extends Element {

	/**
	 * The Logger logger
	 */
	private final static MyLogger logger = LoggerFactory
			.getLogger(Button.class);

	/**
	 * Constructor invoking super.
	 * 
	 * @param by
	 *            : The by reference to set.
	 */
	public Button(By by) {
		super(by);
	}

	/**
	 * constructor invoking super.
	 * 
	 * @param locator
	 *            : The locator to set.
	 * @param locatorType
	 *            : The locatorType to set.
	 */
	public Button(String locator, LocatorType locatorType) {
		super(locator, locatorType);
	}

	/**
	 * Performs the submit operation over any webelement
	 */
	public void submit() {
		logger.info("Click on submit");
		getElement();
		element.submit();
	}

}
