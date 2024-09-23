package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase_open.BaseClass;

public class ExtentReportManager_utility implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;   // UI of the report
	public ExtentReports extent;                // populate common info on the report
	public ExtentTest test;                     // creating test case entries in the report and update status of the test methods

	String repName;
	
	 public void onStart(ITestContext context)
	 {
		 /*  
		  SimpleDateFormat df = new   SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt = new Date();
		  String currentdatetimestamp = df.format(dt); 
		  */
		 
		 String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		 
		 repName = "Test- Report "+ timestamp +".html";
		 sparkReporter = new ExtentSparkReporter(".\\Reports\\"+ repName);
	 
		 
		 
		 
		 sparkReporter.config().setDocumentTitle("Automation Testing");
		 sparkReporter.config().setReportName("Functional Testing");
		 sparkReporter.config().setTheme(Theme.STANDARD);
		 
		 
		 
		 extent= new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 
		 extent.setSystemInfo("Application", "OpenCart");
		 extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
	
		 String os = context.getCurrentXmlTest().getParameter("OS");
		 extent.setSystemInfo("Operating system", os);
		 
		 String br = context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser name", br);
		 
		List <String>  includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
		 extent.setSystemInfo("Groups", includedGroups.toString());
		}
		 
	 
	 }
	
	 public void onTestSuccess(ITestResult result) 
	 {
		 test = extent.createTest(result.getTestClass().getName());  // create a new entry into the report
		 test.assignCategory(result.getMethod().getGroups());  //to display groups in the report
		 test.log(Status.PASS, result.getName()+ " got successfully executed");  // update the status 
	 }
	 
	 
	 
	 
	 public void onTestFailure(ITestResult result)
	 {
		 test = extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL, "Test case FAILED is  "+ result.getName());
		 test.log(Status.FAIL,  result.getThrowable().getMessage());
		 
		 
		 try {
			 String imgPath = new BaseClass().captureScreenshot(result.getName());
			 test.addScreenCaptureFromPath(imgPath);
			 
		 }
		 catch(Exception e1)
		 {
			 e1.printStackTrace();
		 }
		 
    
	 }
	 
	 
	 public void onTestSkipped(ITestResult result) 
	 {
		 test = extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "Test case SKIPPED is  "+ result.getName());	
		 test.log(Status.INFO,  result.getThrowable().getMessage());
     }
	
	 
	 public void onFinish(ITestContext context)
	 {
		 extent.flush();	// this will update all the information in the report	
		 
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\Reports\\"+repName;
		 File extentReport = new File(pathOfExtentReport);
		 
		 try
		 {
			 Desktop.getDesktop().browse(extentReport.toURI());
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 }
     }
