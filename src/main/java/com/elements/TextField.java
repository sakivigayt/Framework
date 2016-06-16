package com.elements;

import org.openqa.selenium.By;

/**
 * checkbox Specific implementation for WebElements
 * 
 */
public class TextField extends Element {

	public TextField(By by) {
		super(by);
	}

	public TextField(String locator, LocatorType locatorType) {
		super(locator, locatorType);
	}

	public void clearandType(String text) {
		getElement().clear();
		getElement().sendKeys(text);
	}

	public void Type(String text) {
		getElement().sendKeys(text);
	}
}
