package com.qalegendbilling.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qalegendbilling.utilities.TestHelperUtility;

public class ViewUserPage extends TestHelperUtility {
 WebDriver driver;
 public ViewUserPage(WebDriver driver) {
	 this.driver=driver;
	 PageFactory.initElements(driver, this);
 }
 @FindBy(xpath = "//div[@class='col-md-6']//p")
 List<WebElement> userdetails;
 
 
 public List<String> getUserDetails() {
	List<String> datas=new ArrayList<String>();
	for(int i=0;i<userdetails.size(); i++) {
		datas.add(page.getMessage(userdetails.get(i)));
	}
	return datas;
	
 }
} 
