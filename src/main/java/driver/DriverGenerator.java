package driver;



import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import properties.GetProperties;

public class DriverGenerator {

	public static WebDriver driver;
	public static String browser;

	//Set directory for drivers
	static File geckoFile=new File("geckodriver.exe");
	static File chromeFile=new File("chromedriver.exe");
	static File edgeFile=new File("MicrosoftWebDriver.exe");
//Determine the d
	public static WebDriver getDriver() {

		browser = GetProperties.getBrowser();
		if (browser.equals("firefox")) {
			driver = getFirefoxDriver();
		}else if(browser.equals("chrome")) {
			driver=getChromeDriver();
		}else if(browser.equals("ie")) {
			driver=getIeDriver();
		}else if(browser.equals("null")) {
			System.out.println("Driver null error!");
		}

		return driver;
	}

	public static WebDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", geckoFile.getAbsolutePath());

		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		WebDriver driver = new ChromeDriver(options);

		return driver;
	}

	public static WebDriver getIeDriver() {
		System.setProperty("webdriver.edge.driver", edgeFile.getAbsolutePath());
		WebDriver driver = new EdgeDriver();

		return driver;
	}
}