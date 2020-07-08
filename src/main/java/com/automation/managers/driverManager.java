package com.automation.managers;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class driverManager {
	private   AndroidDriver<AndroidElement> adriver;
	private   EventFiringWebDriver driver;

	public  AndroidDriver<AndroidElement> getAdriver() {
		return adriver;
	}
	public  void setAdriver(AndroidDriver<AndroidElement> adriver) {
		this.adriver = adriver;
	}
	public  EventFiringWebDriver getDriver() {
		return driver;
	}
	public void setDriver(EventFiringWebDriver driver) {
		this.driver = driver;
	}
	 public void closeDriver() {
		 try {
			 adriver.quit();

		} catch (Exception e) {
			 driver.close();

			// TODO: handle exception
		}
		 }
}
