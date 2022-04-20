package TestCases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import Basic.pom.Reports;

import PageObjects.pom.MagentoHomePage;
import PageObjects.pom.MagentoProductsPage;
import PageObjects.pom.MagentoShoppingCartPage;
import PageObjects.pom.MagentoCheckoutPage;
import PageObjects.pom.MagentoRegisterPage;

public class TestCaseExercise3_Exec {
	private WebDriver driver;
	
	MagentoHomePage magentoHomePage;
	MagentoProductsPage magentoProductsPage;
	MagentoShoppingCartPage magentoShoppingCartPage;
	MagentoCheckoutPage magentoCheckoutPage;
	MagentoRegisterPage magentoRegisterPage;
	
	@BeforeClass
	public static void beforeClass() 
	{
		Reports.fnSetupReport();
	}
	
	@Before
	public void setUp() throws Exception {
		magentoHomePage = new MagentoHomePage(driver);
		driver = magentoHomePage.chromeDriverConnection();
		magentoHomePage.visit("http://live.guru99.com/index.php/tv.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		Reports.fnCloseReport();
	}
	
	/**
	 * Scenario 1
	 * Open “http://live.guru99.com/index.php/tv.html” and add LG LCD tv to the cart. 
	 * @throws InterruptedException
	 */
	@Test
	public void Scenario1() throws InterruptedException {
		try 
		{
			Reports.objTest = Reports.objExtent.createTest("Scenario 1");
			magentoHomePage.clickOnProduct();
			
			magentoProductsPage = new MagentoProductsPage(driver);
			magentoProductsPage.clickOnAddToCart();

			System.out.println("The Scenario 1 was executed successfully");
			Reports.fnLog(Status.PASS, "The Scenario 1 was executed successfully", false);
		} 
		catch (AssertionError e) {
			Reports.fnLog(Status.FAIL, "The Scenario 1 was not executed successfully. Exception: " + e, false);
			System.out.println("The Scenario 1 was not executed successfully. Exception: " + e);
			Assert.fail();
		}
	}

	/**
	 * Scenario 2
	 * Open “http://live.guru99.com/index.php/tv.html” and complete Shopping for SAMSUNG LCD tv (selecting Checkout as Guest) using payment method as Check/Money order. 
	 * @throws InterruptedException
	 */
	@Test
	public void Scenario2() throws InterruptedException {
		try 
		{
			Reports.objTest = Reports.objExtent.createTest("Scenario 2");
			magentoHomePage.clickOnProduct();
			
			magentoProductsPage = new MagentoProductsPage(driver);
			magentoProductsPage.clickOnAddToCart();

			magentoShoppingCartPage = new MagentoShoppingCartPage(driver);
			magentoShoppingCartPage.clickOnProceedtoCheckout();

			magentoCheckoutPage = new MagentoCheckoutPage(driver);
			magentoCheckoutPage.completePurchase();
			
			System.out.println("The Scenario 2 was executed successfully");
			Reports.fnLog(Status.PASS, "The Scenario 2 was executed successfully", false);
		} 
		catch (AssertionError e) {
			Reports.fnLog(Status.FAIL, "The Scenario 2 was not executed successfully. Exception: " + e, false);
			System.out.println("The Scenario 2 was not executed successfully. Exception: " + e);
			Assert.fail();
		}

	}
	
	/**
	 * Scenario 3
	 * Open “http://live.guru99.com/index.php/tv.html” and create new account.
	 * @throws InterruptedException
	 */
	@Test
	public void Scenario3() throws InterruptedException {
		try 
		{
			Reports.objTest = Reports.objExtent.createTest("Scenario 3");
			magentoHomePage.clickOnRegister();

			magentoRegisterPage = new MagentoRegisterPage(driver);
			magentoRegisterPage.register();

			System.out.println("The Scenario 3 was executed successfully");
			Reports.fnLog(Status.PASS, "The Scenario 3 was executed successfully", false);
		} 
		catch (AssertionError e) {
			Reports.fnLog(Status.FAIL, "The Scenario 3 was not executed successfully. Exception: " + e, false);
			System.out.println("The Scenario 3 was not executed successfully. Exception: " + e);
			Assert.fail();
		}
	}

}
