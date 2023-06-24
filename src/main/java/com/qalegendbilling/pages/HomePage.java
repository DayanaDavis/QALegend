package com.qalegendbilling.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qalegendbilling.utilities.TestHelperUtility;

public class HomePage extends TestHelperUtility {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@class='dropdown-toggle']//span")
	WebElement accountHolder;
	@FindBy(xpath = "//button[@class='btn btn-default btn-sm']")
	WebElement endTour;
	@FindBy(xpath = "//li[@class='user-footer']//div[@class='pull-right']")
	WebElement signout;
	@FindBy(xpath = "//span[text()='User Management']")
	WebElement userManagement;
	@FindBy(xpath = "//ul[@class='treeview-menu menu-open']//span[@class='title']")
	List<WebElement> userMgntOptions;
	
	public void clickOnEndTour() {
		page.clickOnElement(endTour);
	}
	public String getAccountHolderName() {
		String accountName=page.getMessage(accountHolder);
		return accountName;
	}
	public void clickOnAccountHolder() {
		page.clickOnElement(accountHolder);
	}
	public LoginPage clickOnSignOut() {
		page.clickOnElement(signout);
		return new LoginPage(driver);
	}
	
	public void clickOnUserManagement() {
		
		wait.waitUntilVisibilityOfElement(10, driver, userManagement);
		page.clickOnElement(userManagement);
		wait.hardWait(2000);
		
	}
	
	public List<String> getUserMgntOptions(){
		List<String> data =new ArrayList<String>();
		for(int i=0;i<userMgntOptions.size();i++) {
			wait.waitUntilVisibilityOfElement(10, driver, userMgntOptions.get(i));
			String option=page.getMessage(userMgntOptions.get(i));
			data.add(option);
		}
		return data;
		
	}
	
	public UserPage clickOnUsers() {
		for(int i=0;i<userMgntOptions.size();i++) {
			wait.waitUntilVisibilityOfElement(10, driver, userMgntOptions.get(i));
			String option=page.getMessage(userMgntOptions.get(i));
			if(option.equalsIgnoreCase("Users")) {
				page.clickOnElement(userMgntOptions.get(i));
			}
		}
		return new UserPage(driver);
	}
	
	
}
