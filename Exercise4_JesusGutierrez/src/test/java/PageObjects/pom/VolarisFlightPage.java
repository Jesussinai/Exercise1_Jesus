package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import Basic.pom.Base;
import Basic.pom.Reports;

public class VolarisFlightPage extends Base{

	public VolarisFlightPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//Variables
	
	int budget = 1500;
	
	//Locators
	
	By pricesOfFlights = By.xpath("//span[@class='price ng-star-inserted']");

	By flightInformation = By.xpath("//div[@class='row no-gutters flightItemDetails']");

	By footPage = By.xpath("/html/body/mbs-root/div/shared-footer/footer/div[2]/div/div[1]/div/p");
	
	By basicOption = By.xpath("/html/body/mbs-root/div/section/mbs-flight/div/div[1]/section/div[6]/mbs-flight-lists/div[2]/div[5]/mbs-flight-fares/div[1]/div/div[1]/mat-card");

	By basicOptionTwo = By.xpath("/html/body/mbs-root/div/section/mbs-flight/div/div[1]/section/div[6]/mbs-flight-lists/div[2]/div[5]/mbs-flight-fares/div[1]/div/div[1]/mat-card");

	By confirmButton = By.xpath("//button[@class='btn btn-large mat-flat-button mat-button-base mat-primary']");
	
	By tuaButton = By.xpath("//div[@class='mat-checkbox-inner-container']");
	
	By total = By.xpath("/html/body/mbs-root/div/section/mbs-flight/div/div[1]/aside/mbs-shopping-cart/div/div[2]/mbs-grand-total-card/div/div[1]/div/div[2]");
	
	//Methods
	
	/**
	 * get flights
	 * @throws InterruptedException
	 */
	public void flights() throws InterruptedException {
		Thread.sleep(6000);
		WaitForLoad("Volaris flights page",true);
		scrollToObject(footPage,false);
		WaitForElement(pricesOfFlights,false);
		
		String bestPrice = getFirstFlight(pricesOfFlights,flightInformation,budget);   //get locator
		By bestPriceLocator = By.xpath(bestPrice); //create new locator from previous best price
		
		scrollToObject(bestPriceLocator,true); // scroll to best price
		scroll(false);
		click(bestPriceLocator,"best flight",false);
		click(basicOption,"Basic option",false);
		
		Reports.fnLog(Status.PASS, "Return flights:", false);
		
		Thread.sleep(6000);
		WaitForLoad("Volaris flights page",true);
		scrollToObject(footPage,false);
		WaitForElement(pricesOfFlights,false);
		
		String bestPriceTwo = getSecondFlight(pricesOfFlights,flightInformation,budget); //get locator
		By bestPriceTwoLocator = By.xpath(bestPriceTwo); //create new locator from previous best price
		
		scrollToObject(bestPriceTwoLocator,true); // scroll to best price
		scroll(false);
		click(bestPriceTwoLocator,"best flight",false);
		click(basicOptionTwo,"Basic option",false);
		
		//finish reservation
		WaitForElement(confirmButton,false);
		click(confirmButton,"Confirm Button",false);
		
		scrollToObject(tuaButton,false);
		click(tuaButton,"TUA",false);
		
		priceComparison(total,budget); // compare expected prices
		
		Thread.sleep(3000);
		
	}
	

}
