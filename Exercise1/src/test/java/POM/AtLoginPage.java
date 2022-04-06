package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;

public class AtLoginPage extends ClsBrowser{
	
	//Locators
	String Email = "iss@agilethought.com";
	String Password = "NewPassword!";
	String UserNameTxt = "//input[@name='loginfmt']";
	By UserNameTxt2 = By.name("loginfmt");
	By UserNameTxt3 = By.name("loginfmt");
	By UserNameTxt4 = By.name("loginfmt");
	By UserNameTxt5 = By.name("loginfmt");
	By UserNameTxt6 = By.name("loginfmt");
	By UserNameTxt7 = By.name("loginfmt");
	String PasswordTxt = "//input[@id='passwordInput']";
	String NextBtn = "//input[starts-with(@id, 'idSIButton')]";
	String StartSessionBtn = "//span[@id='submitButton']"; 
	String KeepSessionDialog = "//div[@id='lightbox']";
	String KeepSessionYesBtn = "//input[starts-with(@id, 'idSIButton')]";
	String Title = "//title";
	
	 
	//Methods
	
	/**
	 * enter the ms email and go to next screen.
	 */
	public void enterCredential() 
	{
		WaitForLoad();
		SendKeys(UserNameTxt2, Email);
		WaitForElementClickable(NextBtn);
		Click(NextBtn);
	}
	
	/**
	 * enter ms email and password, then go to next screen.
	 */
	public void startSession() 
	{
		WaitForLoad();
		WaitForElement(StartSessionBtn);
		WaitForElementClickable(StartSessionBtn);
		SendKeys(PasswordTxt, Password);
		Click(StartSessionBtn);
	}
	
	/**
	 * wait for keep session dialog and click on Yes.
	 */
	public void keepSessionDialog() 
	{
		WaitForLoad();
		Click(KeepSessionYesBtn);
	}
	
	
	
	public void verifyActiveSession() 
	{
		WaitForLoad();
		WaitForElement(Title);
		WebElement objTitle = getGetWebElement(Title);
		String currentTitle = objTitle.getAttribute("innerText");
		Assert.assertEquals("UAT URGENT:  Positions", currentTitle);
	}
	

	
	
}
