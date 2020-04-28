package com.automation.stepdefinitions;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.support.PageFactory;

import com.automation.pageobjects.loginPage;
import com.automation.testbase.driverSetter;
import com.automation.testbase.testbase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;

public class login_definition extends testbase
{
	public static final Logger log = Logger.getLogger(login_definition.class.getName());

loginPage loginpage;

	@Given("^URL for reference domain website$")
	public void launch() throws Exception
	{
		init();
		loginpage=new loginPage(returndriver());
	
	}
	
	@When("^we hit the URL and get the microsoft page$")
     public void click_login() throws IOException, InterruptedException
     
     { 
		Thread.sleep(1000);
     }
	
	//@When("^I enter \"([^\"]*)\" and \"([^\"]*)\" and click on sign in$")
	@When("^I enter my (.*) and (.*) and click on sign in$")

	public void i_enter_name_and_and_click_on_sign_in(String name, String pass) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
//		loginPage lp = new loginPage(driver);
		loginpage.login(name,pass);
	}
	@SuppressWarnings("deprecation")
	@Then("^I verify the the title  (.*) in refrence domain home page$")
	public void verify(String status) throws IOException
	{
//		loginPage lp = new loginPage(driver);
		Assert.assertEquals(loginpage.getvalidlogintext(),status);
	}
}

