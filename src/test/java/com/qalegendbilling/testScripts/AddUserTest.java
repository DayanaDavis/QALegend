package com.qalegendbilling.testScripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

public class AddUserTest extends Base{
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	LoginPage login;
	ExcelUtility excel;
	HomePage home;
	UserPage user;
	AddUserPage addUser;
	RandomUtility random;
	@Test(priority = 1,enabled =true,description = "TC_001_verifyWhetherUserAbleToAddTheNewUser",groups = {"Regression"})
	public void TC_001_verifyWhetherUserAbleToAddTheNewUser() {
		extentTest.get().assignCategory("Regression");
		excel = new ExcelUtility();
		List<ArrayList<String>> datas = ExcelUtility.excelDataReader("LoginPage");
		String username = datas.get(1).get(0);
		String password = datas.get(1).get(1);
		login = new LoginPage(driver);
		login.enterUserCredentials(username, password);
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
        String[] data={uName,fName.concat(" "+lName),job,email};
        List<ArrayList<String>> expectedTable_data=new ArrayList<ArrayList<String>>();
        expectedTable_data.add(new ArrayList<String>(Arrays.asList(data)));
        List<ArrayList<String>> actualTable_data=user.getTableData();
        System.out.println(actualTable_data);
       Assert.assertEquals(actualTable_data,expectedTable_data,ErrorMessages.ERROR_ADD_USER);
       extentTest.get().log(Status.PASS,ExtentLog.ADD_USER_DETAILS);
        
		
	}
	@Test(priority = 1,enabled =true,description = "TC_002_verifyInvalidUserSearch",groups = {"Smoke"})
	public void TC_002_verifyInvalidUserSearch() {
		extentTest.get().assignCategory("Smoke");
		excel = new ExcelUtility();
		List<ArrayList<String>> datas = ExcelUtility.excelDataReader("LoginPage");
		String username = datas.get(1).get(0);
		String password = datas.get(1).get(1);
		login = new LoginPage(driver);
		login.enterUserCredentials(username, password);
		home = login.clickOnLoginButton();
		home.clickOnEndTour();
		home.clickOnUserManagement();
		user=home.clickOnUsers();
		String email=random.getRandomEmail();
		user.enterSearchValue(email);
		String actErrorMsg=user.getErrorMsgForInvalidUser();
		System.out.println(actErrorMsg);
		List<ArrayList<String>> userpage = ExcelUtility.excelDataReader("UserPage");
		String expErrormsg=userpage.get(1).get(0);
		Assert.assertEquals(actErrorMsg, expErrormsg,ErrorMessages.ERROR_INVALID_USER_SEARCH);
		extentTest.get().log(Status.PASS,ExtentLog.INVALID_USER_DETAILS);
	}
}
