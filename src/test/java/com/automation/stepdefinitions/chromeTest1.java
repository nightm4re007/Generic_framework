package com.automation.stepdefinitions;

import org.testng.annotations.Test;
import  org.testng.asserts.SoftAssert;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class chromeTest1 {
	RemoteWebDriver driver;
	 public Boolean checkStatus()
	  {
		  try {
			  driver.findElement(By.xpath("(//*[@name='q'])[1]"));
			  return true;
		  }
		  catch(Exception e)
		  {
			  return false;
		  }
	  }
  @Test
  public void f() {
	  driver.get("http://www.newtours.demoaut.com/");
	  System.out.print(driver.getTitle());
	 String s= driver.findElementByXPath("//*[text()='SIGN-ON']/parent::td").getCssValue("FONT-SIZE");
	 System.out.println(s);
	  
	  
	  
	  
/*	 if(checkStatus())
	 {
		 driver.findElement(By.xpath("(//*[@name='q'])[1]")).sendKeys("bitcoin price");
	 }
	 else
	 {
		 SoftAssert sa = new SoftAssert();
		 sa.assertTrue(checkStatus(),"condition failed as element not present");
		 System.out.println("continued....");

			sa.assertAll();
*/
		// sa.assertEquals("h","e","test case failed");
		 //sa.assertEquals(checkStatus(),true);
				 //sa.fail("test case failed");
		 //Assert.fail("unable to do sendkeys as element not present on webpage");
	 
	// ystem.out.println("continued....");
		/* driver.findElement(By.xpath("(//*[@value='Google Search'])[2]")).click();
		 Select sh = new Select(driver.findElement(By.xpath("(//select)[1]")));
		  sh.selectByVisibleText("Indian Rupee");*/
	/*if(  driver.findElement(By.xpath("(//*[@value='Google Search'])[2]")).isEnabled())
	{
		 driver.findElement(By.xpath("(//*[@value='Google Search'])[2]")).click();
	}*/
		 /* Assert.assertEquals("", "");
		  SoftAssert softAssert = new SoftAssert();
			System.out.println("*** test case two started ***");
			softAssert.assertEquals("Hello", "Hello", "First soft assert failed");
			Assert.fail("");*/
  }
  @BeforeTest
  public void beforeTest() throws IOException, InterruptedException {
	/*  File f= new File("output.txt");
		if(f.delete())
		{
			System.out.println("file deleted successfully");
		}
	  startDocker sd= new startDocker();
	  sd.startFile();*/
	  DesiredCapabilities cap=new DesiredCapabilities();
	  cap.setBrowserName(BrowserType.CHROME);	
		URL u =new URL("http://192.168.99.100:4444/wd/hub");
		 driver = new RemoteWebDriver(u,cap);
		
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			//driver = new ChromeDriver(); // initialising the chrome driver 
  }

  @AfterTest
  public void afterTest() throws IOException, InterruptedException {
	  driver.close();
	  driver.quit();//added this
	/*  stopDocker s=new stopDocker();
	  s.stopFile();*/
  }

}
