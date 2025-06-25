package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static final ExtentReports extent = new ExtentReports();
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    static {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Amazon Test Suite");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Divya Shivaram");
    }

    // Get ExtentReports instance
    public static synchronized ExtentReports getExtent() {
        return extent;
    }

    // Flush the report
    public static synchronized void flushReports() {
        extent.flush();
    }

    // Get the current thread's ExtentTest instance
    public static synchronized ExtentTest getTest() {
        return testThread.get();
    }

    // Set the current thread's ExtentTest instance
    public static synchronized void setTest(ExtentTest test) {
        testThread.set(test);
    }

    // Remove the test from ThreadLocal to prevent memory leaks
    public static synchronized void removeTest() {
        testThread.remove();
    }
}

