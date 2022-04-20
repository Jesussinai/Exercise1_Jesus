package PageObjects.pom;

import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

import org.openqa.selenium.By;


public class MagentoHomePage extends Base{

	public MagentoHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locators
	By productButton = By.xpath("//a[text()='LG LCD']");
	
	By accountButton = By.xpath("//span[text()='Account']");
	By registerButton = By.xpath("//a[@title='Register']");

	//Methods
	
	
	/**
	 * click on product
	 */
	public void clickOnProduct() {
		WaitForLoad("Magento home page",true);
		scrollToObject(productButton,false);
		WaitForElementClickable(productButton,false);
		click(productButton,"LG LCD ",false);
	}
	
	/**
	 * click on register
	 */
	public void clickOnRegister() {
		WaitForLoad("Magento home page",true);
		WaitForElementClickable(accountButton,false);
		click(accountButton,"Account ",true);
		WaitForElementClickable(registerButton,false);
		click(registerButton,"Register ",false);
	}
	
	
	
	
	
	
	
	
	
}
