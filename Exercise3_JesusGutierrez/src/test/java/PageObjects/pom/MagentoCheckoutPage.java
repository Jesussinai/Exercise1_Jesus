package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class MagentoCheckoutPage extends Base{

	public MagentoCheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//Variables
	String userFirstName = "Test name";
	String userLastName = "Test last name";
	String userEmail = "TestEmail@test.com";
	String userAddress = "Test address";
	String userCity = "Test city";
	String userZip = "Test Zip";
	String userTelephone = "1234567890";
	
	String userState ="New York";
	
	//Locators
	By continueButton = By.xpath("//button[@id='onepage-guest-register-button']");
	
	By userFirstNameTxt = By.xpath("//input[@id='billing:firstname']");
	By userLastNameTxt = By.xpath("//input[@id='billing:lastname']");
	By userEmailTxt = By.xpath("//input[@id='billing:email']");
	By userAddressTxt = By.xpath("//input[@id='billing:street1']");
	By userCityTxt = By.xpath("//input[@id='billing:city']");
	By userZipTxt = By.xpath("//input[@id='billing:postcode']");
	By userTelephoneTxt = By.xpath("//input[@id='billing:telephone']");
	By userStateList = By.xpath("//select[@id='billing:region_id']");
	
	By continueButtonTwo = By.xpath("//button[@title='Continue']");
	By continueButtonThree = By.xpath("//button[@onclick='shippingMethod.save()']");
	By checkMoneyOrderButton = By.xpath("//input[@id='p_method_checkmo']");
	By continueButtonFour = By.xpath("//button[@onclick='payment.save()']");
	By placeOrderButton = By.xpath("//button[@title='Place Order']");
	
	By titlePurchase = By.xpath("//h2[@class='sub-title']");
	
	
	//Methods
	
	/**
	 * fill information and buy product
	 */
	public void completePurchase() {
		WaitForLoad("Magento Checkout Page",true);
		WaitForElementClickable(continueButton,false);
		click(continueButton,"Continue Button ",false);
		
		//fill information
		WaitForElement(userFirstNameTxt,false);
		scrollToObject(userFirstNameTxt,false);
		type(userFirstName,userFirstNameTxt,false);
		type(userLastName,userLastNameTxt,false);
		scrollToObject(userEmailTxt,false);
		type(userEmail,userEmailTxt,false);
		type(userAddress,userAddressTxt,false);
		type(userCity,userCityTxt,false);
		selectElementOfList(userStateList,userState,false); //select list item
		type(userZip,userZipTxt,false);
		scrollToObject(userTelephoneTxt,false);
		type(userTelephone,userTelephoneTxt,false);
		
		WaitForElementClickable(continueButtonTwo,false);
		click(continueButtonTwo,"Continue Button ",true);
		
		scrollToObject(continueButtonThree,false);
		WaitForElementClickable(continueButtonThree,false);
		click(continueButtonThree,"Continue Button ",true);
		
		WaitForElementClickable(checkMoneyOrderButton,false);
		scrollToObject(checkMoneyOrderButton,false);
		click(checkMoneyOrderButton,"Check / Money order Button ",true);
		
		WaitForElementClickable(continueButtonFour,false);
		click(continueButtonFour,"Continue Button ",true);
		
		WaitForElementClickable(placeOrderButton,false);
		click(placeOrderButton,"Place Order Button ",true);
		WaitForLoad("Magento Purchase Page",true);
		
		WaitForElement(titlePurchase ,false);
		comparison(titlePurchase,"THANK YOU FOR YOUR PURCHASE!"); //final assert
	}
	

}
