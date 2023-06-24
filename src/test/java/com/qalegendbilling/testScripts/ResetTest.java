package com.qalegendbilling.testScripts;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.qalegendbilling.automationCore.Base;
import com.qalegendbilling.listeners.TestListener;
import com.qalegendbilling.pages.LoginPage;
import com.qalegendbilling.pages.ResetPage;
import com.qalegendbilling.utilities.ExcelUtility;
import com.qalegendbilling.utilities.RandomUtility;

public class ResetTest extends Base {
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	LoginPage login;
	ResetPage reset;

	@Test
	public void TC_001_verifyForgotPasswordMessageForInvalidEmail() {
		ExcelUtility excel = new ExcelUtility();
		RandomUtility random = new RandomUtility();
		List<ArrayList<String>> data = excel.excelDataReader("ResetPage");
		String expErrorMsg = data.get(1).get(0);
		login = new LoginPage(driver);
		reset = login.clickOnForgotPassword();
		String email = random.getRandomEmail();
		reset.enterEmail(email);
		reset.clickOnSendPasswordLink();
		String actErrormsg = reset.getErrorMessage();
		Assert.assertEquals(actErrormsg, expErrorMsg, "Invalid error msg");

	}
}
