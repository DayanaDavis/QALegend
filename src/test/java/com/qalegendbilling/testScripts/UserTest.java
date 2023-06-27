package com.qalegendbilling.testScripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegendbilling.automationCore.Base;
import com.qalegendbilling.constants.ErrorMessages;
import com.qalegendbilling.constants.ExtentLog;
import com.qalegendbilling.listeners.TestListener;
import com.qalegendbilling.pages.AddUserPage;
import com.qalegendbilling.pages.HomePage;
import com.qalegendbilling.pages.LoginPage;
import com.qalegendbilling.pages.UserPage;
import com.qalegendbilling.utilities.ExcelUtility;
import com.qalegendbilling.utilities.RandomUtility;

public class UserTest extends Base {
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	LoginPage login;
	ExcelUtility excel;
	HomePage home;
	UserPage user;
	AddUserPage addUser;
	RandomUtility random;
	
	@Test(priority = 1,enabled = true,description = "TC_001_verifyWhetherUserCanDeleteTheUSer",groups = {"Smoke"})
	public void TC_001_verifyWhetherUserCanDeleteTheUSer() {
		extentTest.get().assignCategory("Smoke");
		excel = new ExcelUtility();
		List<ArrayList<String>> datas = ExcelUtility.excelDataReader("LoginPage");
		String username = datas.get(1).get(0);
		String password = datas.get(1).get(1);
		login = new LoginPage(driver);
		login.enterUserCredentials(username, password);
		List<ArrayList<String>> msg= ExcelUtility.excelDataReader("UserPage");
		String expMsg = msg.get(1).get(0);
		home = login.clickOnLoginButton();
		home.clickOnEndTour();
		home.clickOnUserManagement();
		user=home.clickOnUsers();
		addUser=user.clickOnAddUser();
		String fName= random.getfName();
        addUser.enterFirstName(fName);
        String lName= random.getlName();
        addUser.enterLastName(lName);
        String email=random.getRandomEmail();
        addUser.enterEmail(email);
        String job=addUser.selectRole();
        String uName=random.getUsername();
        addUser.enterUserName(uName);
        String pass=random.getPassword();
        addUser.enterPassword(pass);
        addUser.enterConfirmPass(pass);
        user=addUser.clickOnSaveButton();
        user.enterSearchValue(email);
        user.clickOnDeleteButton();
        user.clickOnAreYouSureDeleteMSg();
        user.enterSearchValue(email);
        String actMsg=user.getErrorMsgForInvalidUser();
        System.out.print(actMsg);
        Assert.assertEquals(actMsg, expMsg,ErrorMessages.ERROR_DELETE_USER);  
        extentTest.get().log(Status.PASS,ExtentLog.USER_DELETE_USER);
	}

}
