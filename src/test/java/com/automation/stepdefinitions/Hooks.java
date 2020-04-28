package com.automation.stepdefinitions;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automation.pageobjects.loginPage;
import com.automation.testbase.testbase;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
public class Hooks extends testbase {
	 Calendar calendar = Calendar.getInstance();
	  SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	  @Before("@skip_scenario")
	    public void skip_scenario(Scenario scenario){
	        System.out.println("SKIP SCENARIO: " + scenario.getName());
	        Assume.assumeTrue(false);
	    }
 @Before
 public void BeforeSteps(Scenario scenario) throws Exception {
		
		 Reporter.assignAuthor("QA - Gaurav Shetty");
		
		// init(); 
	 

 }
 

 @After()
 public void afterScenario(Scenario scenario) throws IOException {
	
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
		  File scrFile = ((TakesScreenshot) adriver).getScreenshotAs(OutputType.FILE);
		  Files.copy(scrFile, destinationPath);   

	 }
	 else
	 {
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  Files.copy(scrFile, destinationPath);   

	 }
 
 //Building up the destination path for the screenshot to save
 //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
 
 //Copy taken screenshot from source location to destination location
 
 //This attach the specified screenshot to the test
 Reporter.addScreenCaptureFromPath(destinationPath.toString());
 } catch (IOException e) {
 } 

 }
 }
 }
 
}