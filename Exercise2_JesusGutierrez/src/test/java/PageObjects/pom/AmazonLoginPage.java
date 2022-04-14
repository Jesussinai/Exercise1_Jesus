package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class AmazonLoginPage extends Base {

	public AmazonLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Variables
	String userEmail = "mevox48425@arpizol.com";
	String userPassword = "Jsgt1024_!";
	
	//Locators
	By userEmailTxt = By.xpath("//input[@type='email']");
	By continueBtn = By.xpath("//input[@id='continue']");
	By userPasswordTxt = By.xpath("//input[@type='password']");
	By signinBtn = By.xpath("//span[@id='auth-signin-button']");
	
	
	//Methods
	
	/**
	 * Login into Amazon page
	 */
	public void login () {
		WaitForLoad("login page",true);
		WaitForElement(userEmailTxt,false);
		type(userEmail, userEmailTxt, true);
		WaitForElementClickable(continueBtn, false);
		click(continueBtn,"CONTINUE BUTTON", false);
		
		WaitForLoad("login page",false);
		WaitForElement(userPasswordTxt,false);
		type(userPassword, userPasswordTxt, true);
		WaitForElementClickable(signinBtn, false);
		click(signinBtn,"SINGIN BUTTON", false);
	}
	
}
