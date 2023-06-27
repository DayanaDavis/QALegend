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
import com.qalegendbilling.pages.HomePage;
import com.qalegendbilling.pages.LoginPage;
import com.qalegendbilling.utilities.ExcelUtility;
import com.qalegendbilling.utilities.RandomUtility;

public class LoginTest extends Base{
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	LoginPage login;
	HomePage home;
	RandomUtility random;
@Test(priority = 1,enabled =true,description = "TC_001_verifyUserLoginsWithValidCredentials",groups = {"Regression"})
public void TC_001_verifyUserLoginsWithValidCredentials() {
	extentTest.get().assignCategory("Regression");
	List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
	String username=data.get(1).get(0);
	String password=data.get(1).get(1);
	login=new LoginPage(driver);
	String expUsername=data.get(1).get(2);
	login.enterUserCredentials(username, password);
	home=login.clickOnLoginButton();
	home.clickOnEndTour();
	String actUsername=home.getAccountHolderName();
	Assert.assertEquals(actUsername,expUsername,ErrorMessages.ERROR_VALID_LOGIN);
	extentTest.get().log(Status.PASS, ExtentLog.USER_VALID_LOGIN);
}
@Test(priority = 1,enabled =true,description = "TC_002_verifyTheErrorMessageDisplayedForInvalidCredentials",groups = {"Regression"})
public void TC_002_verifyTheErrorMessageDisplayedForInvalidCredentials() {
	extentTest.get().assignCategory("Regression");
	ExcelUtility excel=new ExcelUtility();
	List<ArrayList<String>> data=excel.excelDataReader("LoginPage");
	String expErrormsg=data.get(1).get(3);
	random=new RandomUtility();
	String email=random.getUsername();
	String password=random.getPassword();
	login=new LoginPage(driver);
	login.enterUserCredentials(email, password);
	login.clickOnLoginButton();
	String actmsg=login.getErrorMessage();
	Assert.assertEquals(actmsg, expErrormsg,ErrorMessages.ERRO_MSG_INVALID_CREDENTIAL);
	extentTest.get().log(Status.PASS, ExtentLog.USER_INVALID_LOGIN);
}
}
