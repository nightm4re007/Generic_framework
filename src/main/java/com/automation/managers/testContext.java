package com.automation.managers;

import java.io.IOException;

import com.automation.testbase.testbase;


public class testContext  {
	 private pageObjectManager pom;
	 private driverManager ds;
	 
	 public testContext() throws IOException{

	 ds=new driverManager();

	 pom = new pageObjectManager();

	 }
	 public driverManager getDriverManager() {
		 return ds;
		 }
		 
	 public pageObjectManager getPageObjectManager() {
	 return pom;
	 }
	 
	}