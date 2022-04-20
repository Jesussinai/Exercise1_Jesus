package Basic.pom;

import java.util.List;

import org.junit.Assert;
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
		driver.manage().window().maximize();
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
		try {
			driver.findElement(locator).sendKeys(inputText);
			Reports.fnLog(Status.PASS, "- ( "+ inputText +" ) was written on ( "+ locator +" ).", takeScreenshot);
			
		} catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
			Assert.fail();
		}
	}
	
	/**
	 * Click on Web Element
	 * @param locator
	 * @param name
	 * @param takeScreenshot
	 */
	public void click(By locator, String name, boolean takeScreenshot) {
        try
        {
        	driver.findElement(locator).click();
        	Reports.fnLog(Status.PASS, "- "+ name + " was clicked.", takeScreenshot);
        }
        catch (Exception objException)
        {
        	System.out.println("The element was ("+ locator +") not located in the page.");
        	Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found and not clicked.", false);
        	Assert.fail();
        }

	}
	
	/**
	 * Scroll To Web Element
	 * @param locator
	 * @param takeScreenshot
	 */
	public void scrollToObject(By locator,boolean takeScreenshot) {
		try 
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement Element = driver.findElement(locator);
			js.executeScript("arguments[0].scrollIntoView();", Element);
		} 
		catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
			Assert.fail();
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
    	try
    	{
    		objExplicitWait = new WebDriverWait(driver, 30);
        	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        	Reports.fnLog(Status.INFO, "- Web Element found by ( "+ locator +" ).", takeScreenshot);
    	}
    	catch (Exception objException)
    	{
    		System.out.println("The element was ("+ locator +") not located in the page, was waited for 30s");
    		Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
    		Assert.fail();
    	}
    }
    
    /**
     * Wait For Web Element Clickable
     * @param locator
     * @param takeScreenshot
     */
	public void WaitForElementClickable(final By locator, boolean takeScreenshot) {
		try 
		{
			objExplicitWait = new WebDriverWait(driver, 10);
			objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			objExplicitWait.until(ExpectedConditions.elementToBeClickable(locator));
			Reports.fnLog(Status.INFO, "- Web Element found by ( "+ locator +" ).", takeScreenshot);
		} 
		catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page, was waited for 10s.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
			Assert.fail();
		}
    }
	
	/**
	 * Select Element Of List
	 * @param locator
	 * @param optionName
	 */
	public void selectElementOfList(By locator, String optionName, boolean takeScreenshot) {
		try 
		{
			WebElement select = driver.findElement(locator);
			List<WebElement> options = select.findElements(By.tagName("option"));

			for (WebElement option : options) {
				if (optionName.equals(option.getText().trim()))
					option.click();
			}
			Reports.fnLog(Status.PASS, "- ( "+ optionName + " ) was Selected.", takeScreenshot);
		} 
		catch (AssertionError objException) {
			System.out.println("The element was (" + locator + ") not located in the page");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
			Assert.fail();
		}
	}
	
	/**
	 * Comparison of Web Elements
	 * @param locator
	 * @param expected
	 */
	public void comparison(By locator, String expected) {
		try 
		{
			String Actual = driver.findElement(locator).getText();
			Assert.assertEquals(expected, Actual);
			Reports.fnLog(Status.PASS, "( "+expected+" ) is ok", true);
		} 
		catch (AssertionError objException) {
			System.out.println("assertEquals fail");
			Reports.fnLog(Status.FAIL, "assertEquals fail", false);
			Assert.fail();
		}

	}
	
}
