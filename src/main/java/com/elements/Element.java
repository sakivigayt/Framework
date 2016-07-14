package com.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.drivers.DriverFactory;
import com.exception.CustomException;
import com.log.LoggerFactory;
import com.log.MyLogger;

/**
 * Element Class to define different types of Elements
 */
public class Element {

	private final static MyLogger logger = LoggerFactory
			.getLogger(Element.class);

	/**
	 * The WebDriver driver
	 */
	protected WebDriver driver = DriverFactory.getDriver();

	/**
	 * The WebElement element
	 */
	protected WebElement element = null;
	
	protected List<WebElement> listOfElements = null;

	/**
	 * The String locator
	 */
	protected String locator = null;

	/**
	 * The By by
	 */
	protected By by = null;

	/**
	 * The enum LocatorType for different types of Locator.
	 */
	public static enum LocatorType {
		ID, NAME, CLASS_NAME, LINK_TEXT, PARTIAL_LINK_TEXT, CSS_SELECTOR, TAG_NAME, XPATH,
	}

	/**
	 * Constructor creating a by reference.
	 * 
	 * @param by
	 */
	public Element(By by) {
		this.by = by;
	}

	/**
	 * Constructor creating element with a locator and locatorType
	 * 
	 * @param locator
	 * @param locatorType
	 */
	public Element(String locator, LocatorType locatorType) {
		this.locator = locator;
		this.by = getLocatorBy(locator, locatorType);
	}
	
	/**
	 * Method for fetching the locator based on locatorType
	 * 
	 * @param locator
	 * @param locatorType
	 * @return
	 */
	private By getLocatorBy(final String locator, final LocatorType locatorType) {

		switch (locatorType) {

		case ID:
			return By.id(locator);

		case NAME:
			return By.name(locator);

		case CLASS_NAME:
			return By.className(locator);

		case LINK_TEXT:
			return By.linkText(locator);

		case PARTIAL_LINK_TEXT:
			return By.partialLinkText(locator);

		case CSS_SELECTOR:
			return By.cssSelector(locator);

		case TAG_NAME:
			return By.tagName(locator);

		case XPATH:
			return By.xpath(locator);

		default:

			return By.xpath(locator);
		}
	}

	/**
	 * Creates the element with explicit wait.
	 * 
	 * @throws CustomException
	 */
	protected WebElement getElement() {
		logger.info("Getting the object - >" + by.toString());

		return element = (new WebDriverWait(DriverFactory.getDriver(),
				DriverFactory.getConfig().getExplicitWaitTimeout()))
				.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	public List<WebElement> getElements() {
		
		return listOfElements = driver.findElements(by);
		
	}
	/**
	 * clicks on the intended element.
	 */
	public void click() {
		logger.info("Click on the element : " + by.toString());
		getElement().click();
	}

	/**
	 * sends the character stream to intended element.
	 * 
	 * @param arg0
	 *            : chars to send.
	 */
	public void sendKeys(final CharSequence arg0) {
		logger.info("Entering the value in element" + by.toString());
		getElement().clear();
		getElement().sendKeys(arg0);
	}

	/**
	 * Checks if the text is present for intended element.
	 * 
	 * @param text
	 *            - text to check.
	 * @return true if the text is present, else false.
	 */
	public boolean isTextPresent(final String text) {
		logger.info("Check if the text is present");
		return getElement().getText().contains(text);
	}

	/**
	 * checks if a particular element is enabled.
	 * 
	 * @return true if the element is enabled, else false.
	 */
	public boolean isEnabled() {
		logger.info("Check if the element is enabled");
		return getElement().isEnabled();
	}

	/**
	 * checks if a particular element is selected.
	 * 
	 * @return true if the element is selected, else false.
	 */
	public boolean isSelected() {
		logger.info("check if the element is selected");
		getElement();
		return element.isSelected();
	}

	/**
	 * checks if a element id displayed.
	 * 
	 * @return true if the element is displayed, else false.
	 */
	public boolean isDisplayed() {
		logger.info("check if the element is displayed");
		try {
			getElement();
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets the value attribute for the element.
	 * 
	 * @return value attribute of the element.
	 */
	public String getValue() {
		logger.info("Get the value of element");
		getElement();
		return element.getAttribute("value");
	}

	/**
	 * Gets the text for the element.
	 * 
	 * @return text for the element.
	 */
	public String getText() {
		logger.info("Get the text f element");
		getElement();
		return element.getText();
	}

	/**
	 * Gets the tag name of the element.
	 * 
	 * @return tagName for intended element
	 */
	public String getTagName() {
		logger.info("get the tag name of element");
		getElement();
		return element.getTagName();
	}

	/**
	 * Gets the locator
	 * 
	 * @return locator for the element
	 */
	public String getLocator() {
		return locator;
	}

	public void dragDrop(Element elem) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getElement(), elem.getElement());
	}

	public void rightClick() {
		Actions action = new Actions(driver);
		action.contextClick(getElement()).build().perform();
	}

	public void doubleClick() {
		Actions action = new Actions(driver);
		action.doubleClick(getElement()).build().perform();
	}

	public void mouseHover() {
		Actions action = new Actions(driver);
		action.moveToElement(getElement()).build().perform();
	}
}
