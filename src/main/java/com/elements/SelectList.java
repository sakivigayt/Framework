package com.elements;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

/**
 * Support both standard select tag and fake select consists of tag ul and li.
 */
public class SelectList extends Element {

	protected Select select = null;
	protected List<WebElement> options = null;

	public SelectList(final By by) {
		super(by);
	}

	public SelectList(String locator, LocatorType locatorType) {
		super(locator, locatorType);
	}

	/**
	 * De-selects all options in a multi-select list element.
	 */
	public void deselectAll() {
		findElement();
		if (!isMultiple()) {
			throw new UnsupportedOperationException(
					"You may only deselect all options of a multi-select");
		}
		for (WebElement option : options) {
			if (option.isSelected()) {
				option.click();
			}
		}
	}

	public void deselectByIndex(final int index) {
		findElement();
		WebElement option = options.get(index);
		if (option.isSelected()) {
			option.click();
		}
	}

	public void deselectByText(final String text) {
		findElement();
		for (WebElement option : options) {
			if (option.getAttribute("text").equals(text)) {
				if (option.isSelected()) {
					option.click();
				}
				break;
			}
		}
	}

	public void deselectByValue(final String value) {
		findElement();
		for (WebElement option : options) {
			if (option.getAttribute("value").equals(value)) {
				if (option.isSelected()) {
					option.click();
				}
				break;
			}
		}

	}

	protected void findElement() {
		element = driver.findElement(by);
		try {
			select = getNewSelectElement(element);
			options = select.getOptions();
		} catch (UnexpectedTagNameException e) {
			if (element.getTagName().equalsIgnoreCase("ul")) {
				options = element.findElements(By.tagName("li"));
			}
		}
	}

	/**
	 * Returns a new Select element (created to facilitate unit testing).
	 * 
	 * @return
	 */
	protected Select getNewSelectElement(final WebElement element) {
		return new Select(element);
	}

	public List<WebElement> getOptions() {
		findElement();
		return options;
	}

	public String getSelectedText() {
		findElement();
		for (WebElement option : options) {
			if (option.isSelected()) {
				return option.getAttribute("text");
			}
		}

		return null;
	}

	public String[] getSelectedTexts() {
		findElement();
		List<String> textList = new ArrayList<String>();
		for (WebElement option : options) {
			if (option.isSelected()) {
				textList.add(option.getAttribute("text"));
			}
		}

		String[] texts = new String[textList.size()];
		return textList.toArray(texts);
	}

	public String getSelectedValue() {
		findElement();
		for (WebElement option : options) {
			if (option.isSelected()) {
				return option.getAttribute("value");
			}
		}
		return null;
	}

	public String[] getSelectedValues() {
		findElement();
		List<String> valueList = new ArrayList<String>();
		for (WebElement option : options) {
			if (option.isSelected()) {
				valueList.add(option.getAttribute("value"));
			}
		}

		String[] values = new String[valueList.size()];
		return valueList.toArray(values);
	}

	public boolean isMultiple() {
		findElement();
		String value = element.getAttribute("multiple");
		return value != null && !"false".equals(value);
	}

	public void selectByIndex(final int index) {
		findElement();
		WebElement option = options.get(index);
		setSelected(option);
	}

	public void selectByIndex(final int[] indexs) {
		findElement();
		for (int i = 0; i < indexs.length; i++) {
			WebElement option = options.get(indexs[i]);
			setSelected(option);
		}
	}

	public void selectByText(final String text) {
		findElement();
		if (options == null) {
			driver.findElement(By.xpath("//li[text()='" + text + "']")).click();
			return;
		}

		for (WebElement option : options) {
			String selectedText = null;
			if (option.getTagName().equalsIgnoreCase("li")) {
				selectedText = option.getAttribute("title");
			} else {
				selectedText = option.getAttribute("text");
			}
			if (selectedText.equals(text)) {
				setSelected(option);
				break;
			}
		}
	}

	public void selectByText(final String[] texts) {
		findElement();
		for (int i = 0; i < texts.length; i++) {
			for (WebElement option : options) {
				if (option.getAttribute("text").equals(texts[i])) {
					setSelected(option);
					break;
				}
			}
		}
	}

	public void selectByValue(final String value) {
		findElement();
		for (WebElement option : options) {
			if (option.getAttribute("value").equals(value)) {
				setSelected(option);
				break;
			}
		}
	}

	public void selectByValue(final String[] values) {
		findElement();
		for (int i = 0; i < values.length; i++) {
			for (WebElement option : options) {
				if (option.getAttribute("value").equals(values[i])) {
					setSelected(option);
					break;
				}
			}
		}
	}

	private void setSelected(final WebElement option) {
		if (!option.isSelected()) {
			option.click();
		}
	}
}
