package TestCases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import Basic.pom.Reports;
import PageObjects.pom.VolarisHomePage;
import PageObjects.pom.VolarisFlightPage;

public class TestCaseExercise4_Exec {
	private WebDriver driver;
	
	VolarisHomePage volarisHomePage;
	VolarisFlightPage volarisFlightPage;
	
	@BeforeClass
	public static void beforeClass() 
	{
		Reports.fnSetupReport();
	}
	
	@Before
	public void setUp() throws Exception {
		volarisHomePage = new VolarisHomePage(driver);
		driver = volarisHomePage.chromeDriverConnection();
		volarisHomePage.visit("https://www.volaris.com/");
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
		Reports.fnCloseReport();
	}
	
	/**
	 * Exercise 4
	 * 
	 * Search for a flight in Volaris from Guadalajara to Cancùn between 10/29/22 and 11/04/22 
	 * of 7 days with a budget of 1500 MXN.
	 * @throws InterruptedException
	 */
	@Test
	public void test() throws InterruptedException {
		try 
		{
			Reports.objTest = Reports.objExtent.createTest("Exercise 4");
			volarisHomePage.searchFlight();
			volarisFlightPage = new VolarisFlightPage(driver);
			volarisFlightPage.flights();

			System.out.println("The test was executed successfully");
			
			Reports.fnLog(Status.PASS, "The test was executed successfully", false);
		} 
		catch (AssertionError e) {
			Reports.fnLog(Status.FAIL, "The test was not executed successfully. Exception: " + e, false);
			System.out.println("The test was not executed successfully. Exception: " + e);
			Assert.fail();
		}
	}

}