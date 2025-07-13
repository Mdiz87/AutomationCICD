package seleniumFramework.properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Title");
		reporter.config().setReportName("Report Name");
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Tester", "Mahmood");
		return extentReport;
	}
}
