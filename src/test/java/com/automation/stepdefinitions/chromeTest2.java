package com.automation.stepdefinitions;

import org.testng.annotations.Test;
import  org.testng.asserts.SoftAssert;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class chromeTest2 {
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
  public void f() throws MalformedURLException {
	  DesiredCapabilities cap=new DesiredCapabilities();
	  cap.setBrowserName(BrowserType.CHROME);	
		URL u =new URL("http://192.168.99.100:4444/wd/hub");
		 driver = new RemoteWebDriver(u,cap);
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		  driver.get("https://www.google.co.in/");
		  System.out.print(driver.getTitle());
		 String s= driver.findElementByCssSelector("img#hplogo").getCssValue("padding-top");
		 System.out.println(s);
		 String s1= driver.findElementByCssSelector("img#hplogo").getCssValue("height");
		 System.out.println(s1);
	  
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
 /* @BeforeTest
  public void beforeTest() throws MalformedURLException {
	
		 
		 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			//driver = new ChromeDriver(); // initialising the chrome driver 
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
	  driver.quit();//added this

  }
*/
}
