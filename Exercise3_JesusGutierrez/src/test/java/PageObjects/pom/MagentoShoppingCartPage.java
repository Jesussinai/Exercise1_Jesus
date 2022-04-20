package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class MagentoShoppingCartPage extends Base{

	public MagentoShoppingCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locators
	By proceedtoCheckoutButton = By.xpath("//button[@class='button btn-proceed-checkout btn-checkout'][1]");
	
	//Methods
	
	/**
	 * Click On Proceed to Checkout
	 */
	public void clickOnProceedtoCheckout() {
		WaitForLoad("Magento Shopping Cart Page",true);
		WaitForElementClickable(proceedtoCheckoutButton,false);
		click(proceedtoCheckoutButton,"Proceed to Checkout Button ",false);

	}
	

}