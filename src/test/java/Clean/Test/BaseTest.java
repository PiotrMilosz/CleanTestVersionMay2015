package Clean.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import driver.DriverGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

	protected WebDriver driver;
	protected final Logger Log = LoggerFactory.getLogger(getClass());

	// Connected Pages
	

	public WebDriver gimiDriver() {
		return driver;
	}

	@BeforeClass
	public void initialiseDriver() throws IOException {
		driver = DriverGenerator.getDriver();
		Log.info("Driver initialised");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void beforeMethod() {

		System.out.println("We are in before method");
	}
	
	@AfterClass
	public void quitDriver() {
		driver.quit();
		Log.info("Driver terminated");

	}
	
	

}