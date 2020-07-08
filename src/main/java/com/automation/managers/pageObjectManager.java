package com.automation.managers;

import java.io.IOException;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.automation.pageobjects.loginPage;
import com.automation.testbase.testbase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class pageObjectManager extends testbase {
	private loginPage lp;
		

	public loginPage getloginPage() throws IOException{
		 
		 return (lp == null) ?lp = new loginPage(returndriver()):lp;
		 
		 }
		 

}
