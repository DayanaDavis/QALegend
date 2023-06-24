package com.qalegendbilling.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageUtility {
	public void enterText(WebElement element,String text) {
		element.sendKeys(text);
	}
	public String getMessage(WebElement element) {
		return element.getText();
	}
	public void clickOnElement(WebElement elememt) {
		elememt.click();	
	}
	public String getPageTitle(WebDriver driver) {
		String title=driver.getTitle();
		return title;
	}
}
