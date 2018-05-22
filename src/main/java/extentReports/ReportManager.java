package extentReports;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest;
    private static ThreadLocal<ExtentTest> test;
 

    public synchronized static ExtentReports getReporter() {
        if (extent == null)
            createInstance();

        return extent;
    }

    private synchronized static void createInstance() {

        parentTest = new ThreadLocal<>();
        test = new ThreadLocal<>();
        String workingDir = System.getProperty("user.dir");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(workingDir+"\\ExtentReports\\ExtentReportResults.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Leopard regression");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setReportName("Regression test run");
        

        extent = new ExtentReports();
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(htmlReporter);
       
    }

    public synchronized static ThreadLocal<ExtentTest> getParentTest() {
        return parentTest;
    }

    public synchronized static ThreadLocal<ExtentTest> getTest() {
        return test;
    }
}
