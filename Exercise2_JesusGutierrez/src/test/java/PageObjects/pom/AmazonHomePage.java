package PageObjects.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import Basic.pom.Base;

public class AmazonHomePage extends Base{

	public AmazonHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//Locators
	By identifyButton = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	By promotionsButton = By.xpath("//a[@href='/deals?ref_=nav_cs_gb']");
	
	//Methods
	
	/**
	 * click on identify link
	 */
	public void identify () {
		WaitForLoad("home page",true);
		WaitForElementClickable(identifyButton, false);
		click(identifyButton,"IDENTIFY BUTTON", false);
	}
	
	/**
	 * Go to Promotions Page
	 */
	public void goToPromotionsPage () {
		WaitForLoad("home page",true);
		WaitForElementClickable(promotionsButton, false);
		click(promotionsButton,"PROMOTIONS BUTTON", false);
	}
	
}
