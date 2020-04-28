package com.automation.utility;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class utility  {
	public static final Logger log=Logger.getLogger(utility.class.getName());
	
		// TODO Auto-generated constructor stub
		public void genericClick(WebElement element)
		{
			element.click();
			
			//	test.log(LogStatus.PASS,"Clicked on the element:"+element.getText());

			
			

		}
		public void genericSendKeys(WebElement element,String values)
		{
			element.sendKeys(values);
			
				//test.log(LogStatus.PASS,"Entered values >>"+values+"<< on the field:"+element.getText());

			

		}
		public void editSendKeys(WebElement element,String values)
		{
			element.clear();
			element.sendKeys(values);
			
				//test.log(LogStatus.PASS,"Entered values >>"+values+"<< on the field:"+element.getText());

			

		}
		
		public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
			  WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			  wait.until(ExpectedConditions.elementToBeClickable(element));
			//  test.log(LogStatus.PASS,"Waiting for element"+element.getText()+" to be visible:");
			}

	public void JSEClick(WebDriver driver,WebElement element)
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();",element);
		//test.log(LogStatus.PASS,"Clicked on the element:"+element.getText());
	}
	public void JSESendKeys(WebDriver driver,String key,String values)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;

		   js.executeScript("document.getElementById('"+key+"').value='"+values+"'");
		//test.log(LogStatus.PASS,"Entered values>>"+values+"<< on the field:");
	}
public void alertAccept(WebDriver driver)
{
	Alert alert = driver.switchTo().alert();
	alert.accept();
	//test.log(LogStatus.PASS,"Clicked on the following alert Pop-Up:");
}
public void selectByVisibleText(WebElement mainElement,WebElement dropElement)
{
	Select sh = new Select(mainElement);
	  sh.selectByVisibleText(dropElement.getText());
}
public void scrollToEnd(WebDriver driver)
{
	((JavascriptExecutor) driver)
    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
	//test.log(LogStatus.PASS,"Scrolled till the bottom of page:");

	
}
public void scrollToElement(WebDriver driver,WebElement element)
{
	((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView();", element);
	//test.log(LogStatus.PASS,"Focused on element:"+element);
}
public void hoverOnElement(WebDriver driver,WebElement element)
{
	 Actions actions = new Actions(driver);
     
             actions.moveToElement(element)
             .perform();
    // test.log(LogStatus.PASS,"Hovered on element:"+element.getText());
}
public void rightClick(WebDriver driver,WebElement element)
{
	Actions actions = new Actions(driver);
	actions.contextClick(element).perform();
	// test.log(LogStatus.PASS,"Right clicked on element:"+element.getText());
}
public void doubleClick(WebDriver driver,WebElement element)
{
	Actions actions = new Actions(driver);
	actions.doubleClick(element).perform();
	// test.log(LogStatus.PASS,"Double clicked on element:"+element.getText());
}
public void dragAndDrop(WebDriver driver,WebElement source,WebElement target)
{
	Actions actions = new Actions(driver);
	actions.dragAndDrop(source,target).perform();
}
public void refresh(WebDriver driver)
{
	driver.navigate().refresh();
}
public void Tap(AndroidDriver<AndroidElement>  adriver,WebElement element)
{
	   TouchAction t =new TouchAction(adriver);
		t.tap(tapOptions().withElement(element(element))).perform();
}
/*public void uiautomator(AndroidDriver<AndroidElement>  adriver,String attribute,String value)
{
	   adriver.findElementByAndroidUIAutomator('"'+'"'+attribute+'"'+'('+'\'+'"'+value+'"'+'\'+'"'+')'+'"').click();

}*/
public void Tapandhold(AndroidDriver<AndroidElement>  adriver,WebElement element)
{
	   TouchAction t =new TouchAction(adriver);
	   t.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(2))).release().perform();
}

public void swipe(AndroidDriver<AndroidElement>  adriver,WebElement element1,WebElement element2)
{
	TouchAction t=new TouchAction(adriver);
	t.longPress(longPressOptions().withElement(element(element1)).withDuration(ofSeconds(2))).moveTo(element(element2)).release().perform();
}

}
