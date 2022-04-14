package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import PageObjects.pom.AmazonHomePage;
import PageObjects.pom.AmazonLoginPage;
import PageObjects.pom.AmazonPromotionsPage;
import Basic.pom.Reports;

public class TestCaseExercise2_Exec {
	private WebDriver driver;

	AmazonHomePage amazonHomePage;
	AmazonLoginPage amazonLoginPage;
	AmazonPromotionsPage amazonPromotionsPage;

	@Before
	public void setUp() throws Exception {
		amazonHomePage = new AmazonHomePage(driver);
		driver = amazonHomePage.chromeDriverConnection();
		amazonHomePage.visit("https://www.amazon.com.mx/");
		Reports.fnSetupReport();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		Reports.fnCloseReport();
	}
	
	/**
	 * Exercise 2 Test
	 * Login at Amazon page and apply Lightning Deals filter and create products list on report.
	 * @throws InterruptedException
	 */
	@Test
	public void test() throws InterruptedException {
		Reports.objTest = Reports.objExtent.createTest("Exercise 2 Test");
		amazonHomePage.identify();
		
		amazonLoginPage = new AmazonLoginPage(driver);
		amazonLoginPage.login();
		amazonHomePage.goToPromotionsPage();
		
		amazonPromotionsPage = new AmazonPromotionsPage(driver);
		amazonPromotionsPage.chooseLightningDeals();
		amazonPromotionsPage.getProductList();
		
		
	}

}
