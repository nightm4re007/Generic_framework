package com.automation.testbase;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class driverSetter {
	public   AndroidDriver<AndroidElement> adriver;
	public   EventFiringWebDriver driver;

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

}
