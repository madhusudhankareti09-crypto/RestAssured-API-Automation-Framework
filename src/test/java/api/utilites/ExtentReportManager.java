package api.utilites;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;//this is responsible for UI report look & feel
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // START REPORT
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Report Configuration
        sparkReporter.config().setDocumentTitle("Rest Assured Automation Report");
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        // Attach report
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System info
        extent.setSystemInfo("Application", "Pet Store API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "madhu");

    }

    // TEST SUCCESS
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Case Passed");

    }

    // TEST FAILURE
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Case FAILED is");
        test.log(Status.FAIL, result.getThrowable().getMessage());

    }

    // TEST SKIPPED
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP , "Test Case Skipped is");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    // FINISH REPORT
    public void onFinish(ITestContext testContext) {

        extent.flush();

        System.out.println("Report generated successfully");
    }
}