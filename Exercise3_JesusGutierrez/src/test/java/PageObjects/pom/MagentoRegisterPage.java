package PageObjects.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Basic.pom.Base;

public class MagentoRegisterPage extends Base{

	public MagentoRegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//Variables
	String userFirstName = "Test name";
	String userLastName = "Test last name";
	String userEmail = "Emailq@test.com";
	String userPassword = "TestPassword01";
	
	//Locators
	By userFirstNameTxt = By.xpath("//input[@id='firstname']");
	By userLastNameTxt = By.xpath("//input[@id='lastname']");
	By userEmailTxt = By.xpath("//input[@id='email_address']");
	By userPasswordTxt = By.xpath("//input[@id='password']");
	By userConfirmPasswordTxt = By.xpath("//input[@id='confirmation']");
	By registerButton = By.xpath("//button[@title='Register']");
	
	By titleDashboard = By.xpath("//li[@class='success-msg']");
	

	//Methods
	
	/**
	 * fill information to create new account 
	 */
	public void register() {
		WaitForLoad("Register home page",true);
		
		//fill information
		WaitForElement(userFirstNameTxt,false);
		type(userFirstName,userFirstNameTxt,false);
		type(userLastName,userLastNameTxt,false);
		scrollToObject(userEmailTxt,false);
		type(userEmail,userEmailTxt,false);
		type(userPassword,userPasswordTxt,false);
		type(userPassword,userConfirmPasswordTxt,false);
		
		WaitForElementClickable(registerButton,false);
		click(registerButton,"Register ",false);
		
		WaitForElement(titleDashboard,false);
		comparison(titleDashboard,"Thank you for registering with Main Website Store."); //final assert
	
	}

}
