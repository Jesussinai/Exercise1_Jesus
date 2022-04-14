package Basic.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Basic.pom.Base;

public class Base {

	static WebDriver driver;
	public static WebDriverWait objExplicitWait;

	public Base(WebDriver driver) {
		Base.driver = driver;
	}
	
	/**
	 * Chrome driver connection
	 * @return
	 */
	public WebDriver chromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/selenium_driver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}
	
	/**
	 * Go to website
	 * @param url
	 */
	public void visit(String url) {
		driver.get(url);
	}
	

	/**
	 * Write in a text box
	 * @param inputText
	 * @param locator 
	 * @param takeScreenshot
	 */
	public void type(String inputText, By locator, boolean takeScreenshot) {
		Reports.fnLog(Status.INFO, "Step - Type: ( " + inputText + " ) into: (" + locator + ").", false);
		try {
			driver.findElement(locator).sendKeys(inputText);
			Reports.fnLog(Status.PASS, "- "+ inputText +" was written.", takeScreenshot);
			
		} catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
		}
	}
	
	/**
	 * Click on Web Element
	 * @param locator
	 * @param name
	 * @param takeScreenshot
	 */
	public void click(By locator, String name, boolean takeScreenshot) {
		Reports.fnLog(Status.INFO, "Step - Click on "+ name + ".", false);
        try
        {
        	driver.findElement(locator).click();
        	Reports.fnLog(Status.PASS, "- "+ name + " was clicked.", takeScreenshot);
        }
        catch (Exception objException)
        {
        	System.out.println("The element was ("+ locator +") not located in the page.");
        	Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found and not clicked.", false);
        }

	}
	
	/**
	 * Scroll To Web Element
	 * @param locator
	 * @param takeScreenshot
	 */
	public void scrollToObject(By locator,boolean takeScreenshot) {
		Reports.fnLog(Status.INFO, "Step - Scrolling To Web Element: (" + locator + ")", false);
		try 
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement Element = driver.findElement(locator);
			js.executeScript("arguments[0].scrollIntoView();", Element);
			Reports.fnLog(Status.PASS, "- Web Element found.", takeScreenshot);
		} 
		catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
		}
	}
	
	/**
	 * Wait for load web page
	 * @param name
	 * @param takeScreenshot
	 */
    public void WaitForLoad(String name, boolean takeScreenshot) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
        Reports.fnLog(Status.INFO, "Step - Wait for load "+ name +".", takeScreenshot);
    }
	
	/**
	 * Wait Web element
	 * @param locator
	 * @param takeScreenshot
	 */
    public void WaitForElement(final By locator, boolean takeScreenshot) 
    {	
    	Reports.fnLog(Status.INFO, "Step - Looking for the element: " + locator +".", false);
    	try
    	{
    		objExplicitWait = new WebDriverWait(driver, 30);
        	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        	Reports.fnLog(Status.PASS, "- Web Element found.", takeScreenshot);
    	}
    	catch (Exception objException)
    	{
    		System.out.println("The element was ("+ locator +") not located in the page, was waited for 30s");
    		Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
    	}
    }
    
    /**
     * Wait For Web Element Clickable
     * @param locator
     * @param takeScreenshot
     */
	public void WaitForElementClickable(final By locator, boolean takeScreenshot) {
		Reports.fnLog(Status.INFO, "Step - Looking for the element: " + locator+".", false);
		try 
		{
			objExplicitWait = new WebDriverWait(driver, 10);
			objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			objExplicitWait.until(ExpectedConditions.elementToBeClickable(locator));
			Reports.fnLog(Status.PASS, "- Web Element found.", takeScreenshot);
		} 
		catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page, was waited for 10s.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
		}
    }
	
	/**
	 * Create web elements list and reporting
	 * @param locator
	 * @param nameElements
	 */
	public void createElementsList(By locator,String nameElements) {
		Reports.fnLog(Status.INFO, "Step - Getting web elements of: (" + locator + " ).", false);
		int number = 0;
		try 
		{
			driver.findElement(locator);
			List<WebElement> elements = driver.findElements(locator);
			Reports.fnLog(Status.PASS, "- List of web elements created." , false);
			Reports.fnLog(Status.INFO, "- Number of elements found: "+ elements.size()+".", false);
			for (WebElement currentElement : elements) {
				number += 1;
				String elementText = currentElement.getText();
				Reports.fnLog(Status.INFO, "- "+ nameElements +" no: "+ number + " " + elementText + ". ", false);
			}
		} 
		catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
		}
		
	}
    
	
}
