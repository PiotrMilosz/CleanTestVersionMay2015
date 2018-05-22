package Clean.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected final Logger Log = LoggerFactory.getLogger(getClass());
	private static final int DEFAULT_TIMEOUT = 15;
	private static final String ELEMENT_PRESENT_MESSAGE = "ELEMENT PRESENT";
	private static final String ELEMENT_PRESENT_ERROR_FORMAT = "PROBLEM WITH FINDING ELEMENT %s";

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	// waiting for WebeElements
	public void waitElement(WebElement toWait) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(toWait));

	}

	// waiting and clicking WebElements
	public void clickElement(WebElement toClick) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(toClick));
		toClick.click();
	}

	// wait for page load
	public void waitForPageLoad() {
		wait = new WebDriverWait(driver, 20);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	// verification if Element is on page
	protected boolean isElementOnPage(WebElement element) {
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		boolean isElementOnPage = true;
		try {
			// Get location on WebElement is rising exception when element is not present
			element.getLocation();
		} catch (WebDriverException ex) {
			isElementOnPage = false;
		} finally {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		return isElementOnPage;
	}
	
	//waiting until element will be visible on page
	public WebElement forElementVisible(WebElement element) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			return new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			Log.error(ELEMENT_PRESENT_MESSAGE + String.format(ELEMENT_PRESENT_ERROR_FORMAT, element.toString())
					+ e.toString());
			throw e;
		} finally {
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
	}
	
	
	
}
