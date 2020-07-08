package com.automation.runners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.testbase.testbase;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//@RunWith(Cucumber.class)
@CucumberOptions(

		features = "@rerun", glue = { "com/automation/stepdefinitions" }, plugin = {
				"pretty", "json:target/cucumber-reports/Cucumber.json", "junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports",
				"com.cucumber.listener.ExtentCucumberFormatter:" },monochrome = true)
public class failedRunner extends testbase{
	
	/*@BeforeClass
	public static void setup() {
		Calendar calendar = Calendar.getInstance();
		  SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
	ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	extentProperties.setReportPath("src/main/java/com/automation/reports/report" + "_" + formater.format(calendar.getTime()) +".html");
	}*/
    private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass
	public void setUP() throws IOException
	{//
	
		//init();
		testNGCucumberRunner = new TestNGCucumberRunner(failedRunner.class);

		Calendar calendar = Calendar.getInstance();
		  SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
	ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	extentProperties.setReportPath("src/main/java/com/automation/reports/report" + "_" + formater.format(calendar.getTime()) +".html");
		
	}
	@Test(description="login",dataProvider="features")
	public void Login(CucumberFeatureWrapper cFeature)
	{
		testNGCucumberRunner.runCucumber(cFeature.getCucumberFeature());
	}
	@DataProvider(name="features")
	public Object[][] getFeatures()
	{
		return testNGCucumberRunner.provideFeatures();
	}
	@AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\automation\\config\\extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
        testNGCucumberRunner.finish();
    }
	/*@AfterSuite
	 public void AfterSteps() throws IOException {
		 
		closeBrowser();

	 }*/
	
}