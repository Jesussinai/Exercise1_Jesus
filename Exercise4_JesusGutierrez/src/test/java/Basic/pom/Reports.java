package Basic.pom;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports {

	public static ExtentReports objExtent;
	public static ExtentSparkReporter objSpark;
	public static ExtentTest objTest; 
	

	/**
	 * Configure the report on the specified path.
	 */
	public static void fnSetupReport() 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date(System.currentTimeMillis());
		String strSSName = "Test_" + formatter.format(date);
		String reportLocation;
		reportLocation = "C:\\Report\\"+ strSSName.toString() + ".html";
		
		objExtent = new ExtentReports();
		objSpark = new ExtentSparkReporter(reportLocation);
		objSpark.config(
				  ExtentSparkReporterConfig.builder()
				    .theme(Theme.DARK)
				    .documentTitle("Selenium Report")
				    .build()
				);
		objExtent.attachReporter(objSpark);
	}
	
	
	/**
	 * Close the Report Generated
	 */
	public static void fnCloseReport() 
	{
		objExtent.flush();
	}

	/**
	 * Create steps during runtime
	 * @param status
	 * @param description
	 * @param takeScreenshot
	 */
	public static void fnLog(Status status, String description, Boolean takeScreenshot)  
	{
		if(takeScreenshot)
		{ objTest.log(status, description, MediaEntityBuilder.createScreenCaptureFromPath(fnScreenshot()).build());}
		else
		{ objTest.log(status, description); }
		
	}
	
	
	/**
	 * Take a screenshot
	 * @return
	 */
	private static String fnScreenshot() 
	{
		String strFileLocation;
		try 
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MMddyyy_hhmmss");
			Date date = new Date(System.currentTimeMillis());
			String strSSName = "SS_" + formatter.format(date);
			File scrFile = ((TakesScreenshot)Base.driver).getScreenshotAs(OutputType.FILE);
			strFileLocation = "C:\\Report\\images\\"+ strSSName.toString() + ".png";
			FileUtils.copyFile(scrFile, new File(strFileLocation));
			return strFileLocation;
		}
		catch(Exception e) 
		{
			strFileLocation = "";
		}
		return strFileLocation;
	}
	
	
	
	
	
}
