package com.automation.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import static io.appium.java_client.touch.offset.ElementOption.element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.automation.managers.driverManager;
import com.automation.stepdefinitions.Hooks;
import com.automation.testbase.testbase;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;

public class loginPage extends testbase {
	EventFiringWebDriver driver;
	AndroidDriver<AndroidElement> adriver;
	public Boolean flag;

	public loginPage(driverManager d) throws IOException {
		flag = flagselector(d);
		if (flagselector(d)) {
			this.driver = d.getDriver();
			PageFactory.initElements(d.getDriver(), this);
		} else {
			this.adriver = d.getAdriver();
			PageFactory.initElements(new AppiumFieldDecorator(d.getAdriver()), this);
		}
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Expandable Lists']")
	WebElement expandlist;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='People Names']")
	WebElement pn;

	/*
	 * @FindBy(id="passw") WebElement passwd;
	 */

	@FindBy(xpath = "(//input)[3]")
	WebElement un;
	@FindBy(xpath = "(//input)[4]")
	WebElement pw;
	@FindBy(xpath = "(//input)[5]")
	WebElement sb;

	@AndroidFindBy(xpath = "//*[@content-desc='15']")
	WebElement first;

	@AndroidFindBy(xpath = "//*[@content-desc='45']")
	WebElement second;

	@FindBy(xpath = "//*[text()='SIGN-OFF']")
	WebElement validtext;

	/*
	 * @FindBy(id="submitButton") WebElement submit;
	 * 
	 */
	public void navigateurl() throws IOException
	{	
		if (flag) {
			loadData("globalConfig.properties");
			driver.get(OR.getProperty("demourl"));

	}
	
	}

	
	public void login(String username, String password) throws Exception {
	
		if (flag) {
			// for web
			genericSendKeys(un, username);
			genericSendKeys(pw, password);
	        genericClick(sb);

		} else {
			// for native/browser apps
			// genericSendKeys(searchm,username);

			adriver.findElementByAndroidUIAutomator("text(\"Views\")").click();
			Tap(adriver, expandlist);
			adriver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapte']").click();
			Tapandhold(adriver, pn);
		}
		Thread.sleep(6000);
		// uname.sendKeys(username);
		// genericSendKeys(search,username);
		// genericSendKeys(searchm,username);

		//
		/*
		 * adriver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		 * Tap(adriver,expandlist); adriver.
		 * findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").
		 * click(); Tapandhold(adriver, pn);
		 */
		//

		// genericClick(Preferences);

		// WebElement pn=
		// driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		// Thread.sleep(2000);
		// Preferences.click();
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		// genericClick(search);
		// js.executeScript("document.getElementById('passwd').value='"+password+"'");
		/*
		 * genericSendKeys(passwd,password); Thread.sleep(3000);
		 * 
		 * genericClick(submit); Thread.sleep(5000);
		 * 
		 * String text = validtext.getText(); System.out.println(text);
		 */
		// submit.click();
		// waitForElement(driver, 10, validtext);
		//
		/*
		 * adriver.findElementByXPath("//android.widget.TextView[@text='Views']").click(
		 * );
		 * adriver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']")
		 * .click();
		 * adriver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
		 * adriver.findElementByXPath("//*[@content-desc='9']").click(); swipe(adriver,
		 * first,second);
		 */
		//
		// long press //on element// 2 sec// move to another element and you release
		/*
		 * WebElement first=driver.findElementByXPath("//*[@content-desc='15']");
		 * WebElement second=driver.findElementByXPath("//*[@content-desc='45']");
		 */
		//
		/*
		 * adriver.
		 * findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"
		 * );
		 * 
		 * Set<String> contexts=adriver.getContextHandles();
		 * 
		 * //adriver.context(name) for(String contextName :contexts)
		 * 
		 * {
		 * 
		 * System.out.println(contextName);
		 * 
		 * }
		 */
		//
	}

	public String getvalidlogintext() {
		return validtext.getText();
	}

}
