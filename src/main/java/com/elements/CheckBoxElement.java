package com.elements;

import org.openqa.selenium.By;

import com.log.LoggerFactory;
import com.log.MyLogger;

/**
 * checkbox Specific implementation for WebElements
 * 
 */
public class CheckBoxElement extends Element {

	/**
	 * The Logger logger
	 */
	private final static MyLogger logger = LoggerFactory
			.getLogger(CheckBoxElement.class);

	/**
	 * Constructor invoking super.
	 * 
	 * @param by
	 *            : The by reference to set.
	 */
	public CheckBoxElement(final By by) {
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
	public CheckBoxElement(String locator, LocatorType locatorType) {
		super(locator, locatorType);
	}

	/**
	 * Checks if the checkbox is selected or not.
	 */
	public void check() {
		logger.info("Checking the checkbox");
		if (!isSelected()) {
			super.click();
		}
	}

	/**
	 * clicks on the checkbox element.
	 */
	@Override
	public void click() {
		logger.info("click on the ckeckbox");
		super.click();
	}

	/**
	 * checks the selection of a checkbox.
	 * 
	 * @return true if the element is selected.
	 */
	public boolean isSelected() {
		logger.info("is checkbox selected" + element.isSelected());
		getElement();
		return element.isSelected();
	}

	/**
	 * Method to uncheck the selected element.
	 */
	public void uncheck() {
		logger.info("uncheck the element");
		if (isSelected()) {
			super.click();
		}
	}
}
