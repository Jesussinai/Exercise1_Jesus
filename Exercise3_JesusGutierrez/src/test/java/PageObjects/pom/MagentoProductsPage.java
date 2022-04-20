package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class MagentoProductsPage extends Base{

	public MagentoProductsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//Locators
	By addToCartButton = By.xpath("//button[@class='button btn-cart']");
	
	By title = By.xpath("//li[@class='success-msg']");
	
	
	//Methods
	
	//
	/**
	 * Click on add to cart
	 */
	public void clickOnAddToCart() {
		WaitForLoad("Magento product page",true);
		WaitForElementClickable(addToCartButton,false);
		click(addToCartButton,"Add To Cart Button ",false);
		comparison(title,"LG LCD was added to your shopping cart."); //final assert
	}
	

}
