package genericlibrary;

import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation extends genericlibrary.BaseClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public static ExtentTest extentTest;

	@Override
	public void onStart(ISuite suite) {
		String date = LocalDateTime.now().toString().replace(':', '-');
		extentSparkReporter = new ExtentSparkReporter("./AdvanceReport" + date + ".html");
		extentSparkReporter.config().setDocumentTitle("DemoWebShop");
		extentSparkReporter.config().setReportName("DemoAdvance");
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		
		extentTest = extentReports.createTest( suite.getClass().getSimpleName());
		extentTest.log(Status.INFO,suite.getClass().getName());
		
		
	}

	@Override
	public void onFinish(ISuite suite) {
		extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest.log(Status.INFO, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.INFO, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot t = (TakesScreenshot) driver;
		String path = t.getScreenshotAs(OutputType.BASE64);
		String date = LocalDateTime.now().toString().replace(':', '-');
		extentTest.addScreenCaptureFromBase64String(path, date);
		extentTest.log(Status.FAIL,result.getMethod().getMethodName());

	}

}
