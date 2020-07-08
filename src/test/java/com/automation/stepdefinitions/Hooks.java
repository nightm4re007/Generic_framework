package com.automation.stepdefinitions;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;

import com.automation.managers.driverManager;
import com.automation.managers.testContext;
import com.automation.pageobjects.loginPage;
import com.automation.testbase.testbase;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
public class Hooks extends testbase {
	testContext tc;
	 Calendar calendar = Calendar.getInstance();
	  SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	  
	  public Hooks(testContext context) {
		  tc = context;
		  }
	
@Before("@skip_scenario")
public void skip_scenario(Scenario scenario){
	   throw new SkipException("SKIP SCENARIO: " + scenario.getName());
	   }

 @Before
 public void BeforeSteps(Scenario scenario) throws Exception {
		
		 Reporter.assignAuthor("QA - Gaurav Shetty");
			 

 }
 @After("@close_browser")
 public void close_browser() throws IOException
	 {
		 	
		 tc.getPageObjectManager().returndriver().closeDriver();
	 }	
	
  @After()
  public void afterScenario(Scenario scenario) throws IOException {
	try {
		if(scenario.getName().contains("_"))
		{
			
		}
		else
		{
	 if (scenario.isFailed()) {
	 String screenshotName = scenario.getName().replaceAll(" ", "_");
	 try {
	 //This takes a screenshot from the driver at save it to the specified location
		 File destinationPath = new File(System.getProperty("user.dir") + "/src/main/java/com/automation/screenshots/" + screenshotName + formater.format(calendar.getTime()) +".png");
			loadData("globalConfig.properties");

		 if((OR.getProperty("DeviceType").equalsIgnoreCase("mobile")))
		 {
			  File scrFile = ((TakesScreenshot) tc.getPageObjectManager().returndriver().getAdriver()).getScreenshotAs(OutputType.FILE);
			  Files.copy(scrFile, destinationPath);   

		 }
		 else
		 {
			 // File scrFile = ((TakesScreenshot) tc.getDriverManager().).getScreenshotAs(OutputType.FILE);

			  File scrFile = ((TakesScreenshot) tc.getPageObjectManager().returndriver().getDriver()).getScreenshotAs(OutputType.FILE);
			  Files.copy(scrFile, destinationPath);   

		 }
	 
	 //Building up the destination path for the screenshot to save
	 //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
	 
	 //Copy taken screenshot from source location to destination location
	 
	 //This attach the specified screenshot to the test
	 Reporter.addScreenCaptureFromPath(destinationPath.toString());
	 tc.getPageObjectManager().returndriver().closeDriver();
	 } catch (IOException e) {
	 } 

	 }
	 }
	}
	catch(Exception e)
	{
		
	}
	
 }
 
}