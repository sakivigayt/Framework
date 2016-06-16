package com.elements;

import org.openqa.selenium.By;

import com.log.LoggerFactory;
import com.log.MyLogger;

/**
 * Defines the functionalities of locating different WebElements using LinkText.
 * It only defines the functionalities supported by the framework for the
 * selectors.
 */
public class Link extends Element {

	/**
	 * The Logger logger
	 */
	private final static MyLogger logger = LoggerFactory.getLogger(Link.class);

	/**
	 * Initializing the LinkText Element with By locator.
	 * 
	 * @param by
	 *            The By locator
	 */
	public Link(By by) {
		super(by);
	}

	/**
	 * Initializing the LinkText of element.
	 * 
	 * @param locator
	 *            : The link of element
	 * @param locatorType
	 *            : The linktext locatorType.
	 */
	public Link(String locator, LocatorType locatorType) {
		super(locator, locatorType);
	}

	/**
	 * To fetch the Href for any Link element.
	 * 
	 * @return the Href attribute of element.
	 */
	public String getHref() {
		logger.info("Get the href");
		getElement();
		return element.getAttribute("href");
	}

}
