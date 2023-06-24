package com.qalegendbilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qalegendbilling.utilities.TestHelperUtility;

public class LoginPage extends TestHelperUtility{
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "username")
	WebElement usename;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	WebElement loginButton;
	@FindBy(xpath = "//a[@class='btn btn-link']")
	WebElement forgotPassword;
	@FindBy(xpath = "//span[@class='help-block']//strong")
	WebElement errormsg;
	
	public void enterUserCredentials(String user,String paswd) {
		page.enterText(usename, user);
		page.enterText(password, paswd);
	}
	public HomePage clickOnLoginButton() {
		page.clickOnElement(loginButton);
		return new HomePage(driver);
	}
	public ResetPage clickOnForgotPassword() {
		page.clickOnElement(forgotPassword);
		return new ResetPage(driver);
	}
	public String getErrorMessage() {
		String msg=page.getMessage(errormsg);
		return msg;
	}
	
	public String getLoginPageTitle() {
		String title=page.getPageTitle(driver);
		return title;
	}
	
}
