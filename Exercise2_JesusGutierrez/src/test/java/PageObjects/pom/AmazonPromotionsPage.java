package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class AmazonPromotionsPage extends Base{

	public AmazonPromotionsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locators

	By lightningDealsLink = By.xpath("//span[text()='Oferta relámpago']");
	By title = By.xpath("//h1[@class='a-size-extra-large a-spacing-micro']");
	By products = By.xpath("//div[@class ='DealContent-module__truncate_sWbxETx42ZPStTc9jwySW']");

	//Methods
	
	/**
	 * Apply filter: lightning deals on promotions page
	 */
	public void chooseLightningDeals() {
		WaitForLoad("promotions page",true);
		scrollToObject(lightningDealsLink,false);
		click(lightningDealsLink,"LIGHTNING DEALS BUTTON", false);
		scrollToObject(title,false);
	}
	
	/**
	 * get product list
	 */
	public void getProductList() {
		WaitForLoad("promotions page",false);
		WaitForElement(products,true);
		createElementsList(products,"Product");
	}

}
