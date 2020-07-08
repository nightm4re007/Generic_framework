package com.automation.stepdefinitions;

import java.io.IOException;
import java.util.logging.Logger;

import com.automation.managers.testContext;
import com.automation.pageobjects.loginPage;
import com.automation.testbase.testbase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class login_definition extends testbase
{
	public static final Logger log = Logger.getLogger(login_definition.class.getName());

testContext tc;
loginPage loginpage;

public login_definition(testContext context) throws IOException {
tc = context;
loginpage = tc.getPageObjectManager().getloginPage();
}
	@Given("^URL for newtours domain website$")
	public void launch() throws Exception
	{
		//init("urlnew");
	
	}
	
	@When("^we hit the URL and get the newtours page$")
     public void click_login() throws IOException, InterruptedException
     
     { 
	loginpage.navigateurl();
		
     }
	
	@When("^I enter my (.*) and (.*) and click on sign in$")

	public void i_enter_name_and_and_click_on_sign_in(String name, String pass) throws Throwable {
	
		loginpage.login(name,pass);
	}
	@SuppressWarnings("deprecation")
	@Then("^I verify the the title  (.*) in refrence domain home page$")
	public void verify(String status) throws IOException
	{
		Assert.assertEquals(loginpage.getvalidlogintext(),status);
		 tc.getPageObjectManager().returndriver().closeDriver();
	}
}

