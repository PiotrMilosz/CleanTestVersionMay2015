package screenshoters;

import com.aventstack.extentreports.MediaEntityBuilder;

import Clean.Test.BaseTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;
import java.io.IOException;


import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static extentReports.ReportManager.getTest;


public class ScreenshotProvider extends TestListenerAdapter {
	protected final static  Logger Log = LoggerFactory.getLogger(Logger.class.getName());
	protected static String TEST_SCREENSHOTS= "/test-output/screenshots/";
	protected static BaseTest bt;
	protected static Object testClass;

    public synchronized static void takeScreenshot(ITestContext context) {

        String path = generateFilePath("Evidence", context.getName());
        takeScreenshot(path);
        try {
            getTest().get().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (IOException e) {
            Log.error("Cannot add the screenshot to the report: " + e.getMessage());
        }
    }

    public synchronized static void takeScreenshot(ITestResult tr) {

        String name = tr.getTestContext().getName();
        testClass = tr.getInstance();
        String status;
        String path;
        try {
            if (tr.isSuccess()) {
                status = "Success";
                path = generateFilePath(status, name);
                takeScreenshot(path);
                getTest().get().pass(status, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            } else {
                status = "Failed";
                path = generateFilePath(status, name);
                takeScreenshot(path);
                getTest().get().fail(tr.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            }
        } catch (IOException e) {
            Log.error("Cannot add screenshot to the report: " + e.getMessage());
        }
    }

    private synchronized static void takeScreenshot(String path) {
    		
        WebDriver driver =((BaseTest) testClass).gimiDriver();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(path);
        try {
            FileUtils.copyFile(srcFile, destFile);
            Log.info("Screenshot was taken.");
        } catch (IOException e) {
            Log.error("Cannot to create the screenshot in the target path: " + e.getMessage());
        }
    }

    private synchronized static String generateFilePath(String status, String name) {

        String date = now().format(ofPattern("yyyy-MM-dd_HHmmss"));
        String dateFolderName = now().format(ofPattern("yyyy-MM-dd"));
        String testFolder = name.replaceAll(" ", "_") + "_" + dateFolderName;
        String result = status + "_" + name.replaceAll(" ", "_") + "_" + date;

        return TEST_SCREENSHOTS + testFolder + "/" + result + ".png";
    }
}
