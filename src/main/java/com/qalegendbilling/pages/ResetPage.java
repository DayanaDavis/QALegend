package com.qalegendbilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qalegendbilling.utilities.TestHelperUtility;

public class ResetPage extends TestHelperUtility {
	public WebDriver driver;

	public ResetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement email;
	@FindBy(xpath = "//button [@class='btn btn-primary']")
	WebElement sendPasswordLink;
	@FindBy(xpath = "//span[@class='help-block']//strong")
	WebElement errormsg;
	
	public void enterEmail(String mailId) {
		page.enterText(email, mailId);
	}
	public void clickOnSendPasswordLink() {
		page.clickOnElement(sendPasswordLink);
	}
	public String getErrorMessage() {
		String msg=page.getMessage(errormsg);
		return msg;
	}
}
