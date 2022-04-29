package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class VolarisHomePage extends Base{

	public VolarisHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locators
	By originButton = By.xpath("//a[@class='btnSearch radius-6']");

	By stateContainer = By.xpath("//ul[@class='ng-star-inserted']");
	
	By guadlajaraButton = By.xpath("//*[text()='Guadalajara']");
	By cancunButton = By.xpath("//*[text()='Cancún']");
	
	By dateContainer = By.xpath("//select[@class='monthselect'][1]");
	
	By dateList = By.xpath("//*[text()=' abril ']");
	
	By flightDay = By.xpath("//td[@class='weekend datecell-20221029 customfare available']");
	
	By returnDay = By.xpath("//td[@class='datecell-20221104 returnVisible returnCustomfare available']");

	By continueButton = By.xpath("//button[@class='btn-calendar d-none d-md-block mat-flat-button mat-button-base mat-secondary']");
	
	By searchFlightsButton = By.xpath("//button[@class='btn btn-large col-12 search-btn mat-flat-button mat-button-base mat-primary']");
	

	//Methods
	
	/**
	 * Search flight
	 * @throws InterruptedException
	 */
	public void searchFlight() throws InterruptedException {
		WaitForLoad("Volaris home page",false);
		
		//select Guadalajara
		WaitForElementClickable(originButton,false);		
		click(originButton,"origen",false);

		WaitForElement(stateContainer,false);
		
		scrollToObject(guadlajaraButton,false);
		click(guadlajaraButton,"Guadalajara",false);
		
		//select Cancún
		WaitForLoad("Volaris home page",true);
		
		WaitForElement(stateContainer,false);
		scrollToObject(cancunButton,false);
		click(cancunButton,"Cancun",false);
		
		//select date
		WaitForLoad("Volaris home page",false);
		WaitForElement(dateContainer,false);
		selectElementOfList(dateList,"octubre",false);
		
		//select day
		WaitForElement(flightDay,false);
		click(flightDay,"Flight Day 29",false);
		
		//select return day
		WaitForElement(returnDay,false);
		click(returnDay,"Return Day 4",true);
		
	
		WaitForElementClickable(continueButton,false);
		click(continueButton,"Hecho button",false);
		WaitForElementClickable(searchFlightsButton,false);
		click(searchFlightsButton,"Search flights button",false);
		WaitForLoad("Volaris home page",false);
	}

}
