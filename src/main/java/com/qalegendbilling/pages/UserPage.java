package com.qalegendbilling.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qalegendbilling.utilities.TestHelperUtility;

public class UserPage extends TestHelperUtility {
	public WebDriver driver;

	public UserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-block btn-primary']")
	WebElement addUser;
	@FindBy(xpath = "//input[@class='form-control input-sm']")
	WebElement search;
	@FindBy(xpath = "//table[@id='users_table']/tbody/tr")
	List<WebElement> rawElements;
	@FindBy(xpath = "//table[@id='users_table']/tbody/tr/td")
	List<WebElement> cellElements;
	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement invalidUserErrorMsg;
	@FindBy(xpath = "//button[@class='btn btn-xs btn-danger delete_user_button']")
	WebElement deleteUser;
	@FindBy(xpath = "//button[@class='swal-button swal-button--confirm swal-button--danger']")
	WebElement areyouSureDelete;
	@FindBy(xpath = "//a[@class='btn btn-xs btn-info']")
	WebElement viewButton;

	public AddUserPage clickOnAddUser() {
		page.clickOnElement(addUser);
		return new AddUserPage(driver);
	}
	public void enterSearchValue(String email) {
        wait.waitUntilVisibilityOfElement(50,driver,search);
        page.enterText(search, email);
        wait.hardWait(5000);
    }
	public List<ArrayList<String>> getTableData(){
		List<ArrayList<String>> data=table.get_Dynamic_TwoDimension_TablElemnts(rawElements, cellElements);
		return data;
	}
	public String getErrorMsgForInvalidUser(){
        wait.waitUntilVisibilityOfElement(50,driver,invalidUserErrorMsg);
        String msg=page.getMessage(invalidUserErrorMsg);
        return msg;
    }
	public void clickOnDeleteButton() {
		wait.waitUntilVisibilityOfElement(10, driver, deleteUser);
		page.clickOnElement(deleteUser);
	}
	public void clickOnAreYouSureDeleteMSg() {
		wait.waitUntilVisibilityOfElement(10, driver, areyouSureDelete);
		page.clickOnElement(areyouSureDelete);
	}
	public ViewUserPage clickOnViewButton() {
		page.clickOnElement(viewButton);
		return new ViewUserPage(driver);
	}

}
