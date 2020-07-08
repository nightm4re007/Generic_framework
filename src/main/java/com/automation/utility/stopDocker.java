package com.automation.utility;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

public class stopDocker {
	Boolean flag=false;
  @Test
  public void stopFile() throws IOException, InterruptedException {
	  Runtime runtime= Runtime.getRuntime();
	  //System.getProperty("user.dir") + "/docker/docker-up.bat"
	  runtime.exec("cmd /c start docker-down.bat");
	
	  String file="output.txt";
	 
	  Calendar cal= Calendar.getInstance();
	  cal.add(Calendar.SECOND,45);
	 long stopnow= cal.getTimeInMillis();
	 //sleep for 3 secs
	 Thread.sleep(3000);
	while(System.currentTimeMillis()<stopnow)
	 {
		 if(flag)
		 {
			 break;
		 }
		 BufferedReader reader = new BufferedReader(new FileReader(file));
	  String currentLine=reader.readLine();
	  while(currentLine!=null && !flag)
	  {
		  if(currentLine.contains("selenium-hub exited"))
				  {
			  System.out.println("Started grid:@@@@@@@@@@@@@ docker grid is down@@@@@@@@@@@@@@@@@");

			  flag=true;

			  break;
				  }
		  currentLine=reader.readLine();

	  }
	  reader.close();

	 }
	  Assert.assertTrue(flag);
	 File f = new File("output.txt");
	 if(f.delete())
	 {
		 System.out.println("output file deleted successfully");
	 }
  }
}
