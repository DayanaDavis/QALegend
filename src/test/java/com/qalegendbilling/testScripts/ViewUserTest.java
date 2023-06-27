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
import com.qalegendbilling.pages.ViewUserPage;
import com.qalegendbilling.utilities.ExcelUtility;
import com.qalegendbilling.utilities.RandomUtility;

public class ViewUserTest extends Base{
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	LoginPage login;
	ExcelUtility excel;
	HomePage home;
	UserPage user;
	AddUserPage addUser;
	RandomUtility random;
	ViewUserPage viewUser;
	@Test(priority = 1,enabled = true,description = "verifyTheDetailsDisplayedOnViewUserPage",groups = {"Smoke"})
	public void verifyTheDetailsDisplayedOnViewUserPage() {
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
        String percentage=addUser.enterSalesPercentage();
        user=addUser.clickOnSaveButton();
        user.enterSearchValue(email);
        viewUser=user.clickOnViewButton();
        List<ArrayList<String>> userdetails=excel.excelDataReader("ViewUserPage");
        List<String> expected_details=new ArrayList<String>();
        expected_details.add((userdetails.get(0).get(0))+" ".concat(email));
        expected_details.add((userdetails.get(1).get(0))+" ".concat(job));
        expected_details.add((userdetails.get(2).get(0))+" ".concat(uName));
        expected_details.add((userdetails.get(3).get(0))+" ".concat(percentage)+"%");
        expected_details.add((userdetails.get(4).get(0)));
        expected_details.add((userdetails.get(5).get(0))+" ".concat(percentage)+"%");
        expected_details.add((userdetails.get(6).get(0)));
        System.out.println(expected_details);
        List<String> actdetails=viewUser.getUserDetails();
        System.out.println(actdetails);
        Assert.assertEquals(actdetails, expected_details,ErrorMessages.ERROR_USER_DETAILS);
        extentTest.get().log(Status.PASS,ExtentLog.VIEW_USER_DETAILS);
	}
}
