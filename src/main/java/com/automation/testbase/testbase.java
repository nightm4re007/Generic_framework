package com.automation.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import com.automation.excelreader.excelReader;
import com.automation.listener.webeventlistener;
import com.automation.pageobjects.loginPage;
import com.automation.utility.utility;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testbase extends utility {
	public static final Logger log = Logger.getLogger(testbase.class.getName());
	public WebDriver dr;
	public AndroidDriver<AndroidElement> adriver;
	public static AppiumDriverLocalService service;
	public EventFiringWebDriver driver;
	public webeventlistener eventlistener;
	public static RequestSpecification req;
	public Boolean flag;
	public Properties OR = new Properties();
	driverSetter d = new driverSetter();

	FileInputStream f;

	public void loadData(String confile) throws IOException {

		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/automation/config/" + confile);
		f = new FileInputStream(file);
		OR.load(f);

	}

	public void refresh() {
		driver.navigate().refresh();
	}

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	}

	public void init() throws Exception {
		loadData("globalConfig.properties");
		if (OR.getProperty("DeviceType").equalsIgnoreCase("desktop")) {
			getbrowser(OR.getProperty("browser"));
			geturl(OR.getProperty("url"));

		} else if (OR.getProperty("DeviceType").equalsIgnoreCase("mobile")) {
			if ((OR.getProperty("AppType").equalsIgnoreCase("native"))
					|| (OR.getProperty("AppType").equalsIgnoreCase("hybrid"))) {
				appinit();
			} else {
				appinit();
				geturl(OR.getProperty("url"));

			}

		} else {
			throw new Exception("Kindly add valid device Type");
		}

		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		// return driver;
	}

	public EventFiringWebDriver getbrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			dr = new ChromeDriver(); /* initialising the chrome driver */
			driver = new EventFiringWebDriver(dr); /* initialising event firing webdriver as it is used to trigger the event */
			eventlistener = new webeventlistener(); /*
													 * initialising the webevent listener as it is used to catch the
													 * event
													 */
			driver.register(eventlistener); /* registering the event firing webdriver with web event listener object */
			log.info("creating object of" + browser);

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("firefox");
			dr = new FirefoxDriver();
			driver = new EventFiringWebDriver(dr);
			eventlistener = new webeventlistener();
			driver.register(eventlistener);
			log.info("creating object of" + browser);

		}

		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			System.out.println(" Executing on IE");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dr = new InternetExplorerDriver(ieCapabilities);
			driver = new EventFiringWebDriver(dr);
			eventlistener = new webeventlistener();
			driver.register(eventlistener);
			log.info("creating object of" + browser);

		}

		else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		return driver;

	}

	public void appinit() throws MalformedURLException {

		service = startServer();

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", (OR.getProperty("deviceName")));

		capabilities.setCapability("udid", (OR.getProperty("udid")));
		capabilities.setCapability("platformName", (OR.getProperty("platformName")));
		capabilities.setCapability("platformVersion", (OR.getProperty("platformVersion")));
		if (OR.getProperty("AppType").equalsIgnoreCase("browser")) {
			capabilities.setCapability("browserName", (OR.getProperty("browserName")));
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		} else {
			capabilities.setCapability("automationName", "Appium");

			// capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
			// "uiautomator2");
			capabilities.setCapability("appPackage", (OR.getProperty("appPackage")));
			capabilities.setCapability("appActivity", (OR.getProperty("appActivity")));

		}
		adriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	private void geturl(String url) throws IOException {

		log.info("navigating to" + url);
		loadData("globalConfig.properties");
		if ((OR.getProperty("DeviceType").equalsIgnoreCase("mobile"))) {
			adriver.get(url);

		} else {
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		}

	}

	public String[][] getdata(String excelname, String sheetname) {
		String path = System.getProperty("user.dir") + "/src/main/java/com/automation/testdata/" + excelname;
		excelReader excel = new excelReader(path);
		String[][] data = excel.getDataFromSheet(sheetname, excelname);
		return data;

	}

	public Iterator<String> getAllWindows() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	}

	public void closeBrowser() throws IOException {
		driver.quit();
		log.info("driver closed");
	}

	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "/src/main/java/com/automation/testdata/" + excelName;
		excelReader excel = new excelReader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	public AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			loadData("globalConfig.properties");

			req = new RequestSpecBuilder().setBaseUri(OR.getProperty("baseuri"))
					.addQueryParam(OR.getProperty("querykey"), OR.getProperty("queryval"))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;

	}

	public driverSetter returndriver() throws IOException {
		loadData("globalConfig.properties");

		if ((OR.getProperty("DeviceType").equalsIgnoreCase("mobile"))) {
			d.setAdriver(adriver);
		} else {
			d.setDriver(driver);
		}
		return d;
	}

	public Boolean flagselector(driverSetter d) {
		ArrayList l = new ArrayList();
		l.add(d.getDriver());
		if (!l.contains(null)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
