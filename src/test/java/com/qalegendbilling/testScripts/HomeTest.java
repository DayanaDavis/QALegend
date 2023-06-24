package com.qalegendbilling.testScripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.qalegendbilling.automationCore.Base;
import com.qalegendbilling.listeners.TestListener;
import com.qalegendbilling.pages.HomePage;
import com.qalegendbilling.pages.LoginPage;
import com.qalegendbilling.utilities.ExcelUtility;

public class HomeTest extends Base {
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	LoginPage login;
	ExcelUtility excel;
	HomePage home;

	@Test(priority = 1, enabled = true, description = "TC_001_verifyWhetherUserAbleToSignoutFromTheApplication", groups = {
			"Regression" })
	public void TC_001_verifyWhetherUserAbleToSignoutFromTheApplication() {
		extentTest.get().assignCategory("Regression");
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String username = data.get(1).get(0);
		String password = data.get(1).get(1);
		login = new LoginPage(driver);
		String expTitle = data.get(1).get(4);
		login.enterUserCredentials(username, password);
		home = login.clickOnLoginButton();
		home.clickOnEndTour();
		home.clickOnAccountHolder();
		login = home.clickOnSignOut();
		String acttitile = login.getLoginPageTitle();
		Assert.assertEquals(acttitile, expTitle, "Invalid title");
	}

	@Test
	public void TC_002_verifyUserManagementSubTabs() {
		excel = new ExcelUtility();
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String username = data.get(1).get(0);
		String password = data.get(1).get(1);
		login = new LoginPage(driver);
		List<String> expdata = excel.getExcelAsArrayList("UserManagemntOptions");
		System.out.println(expdata);
		login.enterUserCredentials(username, password);
		home = login.clickOnLoginButton();
		home.clickOnEndTour();
		home.clickOnUserManagement();
		List<String> actdata = home.getUserMgntOptions();
		System.out.println(actdata);
		Assert.assertEquals(actdata, expdata, "Invalid userManagement Options");
	}

}
