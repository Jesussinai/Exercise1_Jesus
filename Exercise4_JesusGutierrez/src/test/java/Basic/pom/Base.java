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
        Reports.fnLog(Status.INFO, "- Step - Wait for load "+ name +".", takeScreenshot);
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
        	if(takeScreenshot) {
        		Reports.fnLog(Status.INFO, "- Web Element found by ( "+ locator +" ).", takeScreenshot);
        	}
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
			if(takeScreenshot) {
        		Reports.fnLog(Status.INFO, "- Web Element found by ( "+ locator +" ).", takeScreenshot);
        	}
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
		catch (Exception objException) {
			System.out.println("The element was (" + locator + ") not located in the page");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") was not found.", false);
			Assert.fail();
		}
	}
	
	/**
	 * Scroll
	 * @param dir
	 */
	public void scroll(boolean dir) {
		if(dir) {
			((JavascriptExecutor) driver).executeScript("scroll(0,50)");
		}else {
			((JavascriptExecutor) driver).executeScript("scroll(0,-50)");
		}
	}
	
	
	/**
	 * Obtain the schedule and price of the first flights that are less than or equal to the defined amount
	 * @param locator
	 * @param locatortwo
	 * @param amount
	 * @return 
	 */
	public static String getFirstFlight(By locator, By locatorTwo, int amount) {
		Reports.fnLog(Status.INFO, "- Step - Getting web elements of: (" + locator + " ).", false);
		String bestFlightLocator = "";
		try {
			driver.findElement(locator);
			driver.findElement(locatorTwo);
			List<WebElement> elements = driver.findElements(locator);
			List<WebElement> hourElements = driver.findElements(locatorTwo);

			int numnberOfElements = elements.size();
			int i = 0;
			int n = 0;
			int cn = 0;
			String[] hour;
			hour = new String[(hourElements.size()) * 2];
			String recommendedFlight = "";
			int maxAmount = amount;

			if (numnberOfElements == 0) {
				Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") is empty.", false);
				Assert.fail();
			}

			// Fill the array with the available information
			for (WebElement currentElementtwo : hourElements) {
				String hourElementText = currentElementtwo.getText();
				hourElementText = hourElementText.replace("keyboard_arrow_down", "");
				hourElementText = hourElementText.replace("é", "e");
				hour[i] = hourElementText;
				//System.out.println(hour[i]);
				i++;
				i++; // Always skip one for the second price of the same flight
			}

			Reports.fnLog(Status.PASS, "- List of flights created.", false);
			Reports.fnLog(Status.INFO, "- Number of flights found: " + (numnberOfElements - 7) / 2 + ".", false);
			Reports.fnLog(Status.INFO, "- Flights with a price less than or equal to: " + amount + ".", false);

			for (WebElement currentElement : elements) {
				n++;
				//System.out.println(n);
				String elementText = currentElement.getText();

				// Eliminate letters of price
				String price = currentElement.getText();
				price = price.replace(" MXN", "");
				price = price.replace("$", "");
				price = price.replace(",", "");
				int priceConverToInt = Integer.parseInt(price);// Convert to int

				if (n > 7) { // Ignore up to 7 because elements before 7 are not corresponding to prices
					if (priceConverToInt <= amount) { // Compare the prices with the maximum quantity

						if (((n) % 2) == 0) {
							//System.out.println(elementText);
							Reports.fnLog(Status.PASS, "- Flight found!! departure time: " + hour[cn] + ".", false);
							Reports.fnLog(Status.INFO, "- Regular price: " + elementText + ".", false);

							if (priceConverToInt < maxAmount) {
								maxAmount = priceConverToInt;
								recommendedFlight = hour[cn];
								bestFlightLocator = elementText;
							}

						} else {
							//System.out.println(elementText);
							Reports.fnLog(Status.INFO, "- v.club price: " + elementText + ".", false);
						}
					}
					cn++;
				}
			}
			//System.out.println(bestFlightLocator);
			System.out.println("- cheapest recommended first flight: " + recommendedFlight + ".");
			Reports.fnLog(Status.PASS, "- Cheapest recommended flight: " + recommendedFlight + ".", false);// Show the
																											// Cheapest
																											// flight

		} catch (Exception objException) {
			System.out.println("The element was (" + locator + ") or ( " + locatorTwo + ") not located in the page.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") or ( " + locatorTwo + ") was not found.",
					false);
			Assert.fail();
		}
		bestFlightLocator = bestFlightLocator.replace("MXN", "");
		bestFlightLocator = "//span[text()='" + bestFlightLocator + "']/following-sibling::mat-icon"; // Create locator string
		return bestFlightLocator; // Returns the xpath of the lowest price

	}
	
	
	/**
	 * Obtain the schedule and price of the flights that are less than or equal to the defined amount
	 * @param locator
	 * @param locatortwo
	 * @param amount
	 */
	public static String getSecondFlight(By locator, By locatorTwo, int amount) {
		Reports.fnLog(Status.INFO, "- Step - Getting web elements of: (" + locator + " ).", false);
		String bestFlightLocator = "";
		try {
			driver.findElement(locator);
			driver.findElement(locatorTwo);
			List<WebElement> elements = driver.findElements(locator);
			List<WebElement> hourElements = driver.findElements(locatorTwo);

			int numnberOfElements = elements.size();
			int i = 0;
			int n = 0;
			int cn = 0;
			String[] hour;
			hour = new String[(hourElements.size())];
			String recommendedFlight = "";

			int maxAmount = amount;

			if (numnberOfElements == 0) {
				Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") is empty.", false);
				Assert.fail();
			}

			// Fill the array with the available information
			for (WebElement currentElementtwo : hourElements) {
				String hourElementText = currentElementtwo.getText();
				hourElementText = hourElementText.replace("keyboard_arrow_down", "");
				hourElementText = hourElementText.replace("é", "e");
				hour[i] = hourElementText;
				//System.out.println(hour[i]);
				i++;
			}

			Reports.fnLog(Status.PASS, "- List of flights created.", false);
			Reports.fnLog(Status.INFO, "- Number of flights found: " + (numnberOfElements - 7) + ".", false);
			Reports.fnLog(Status.INFO, "- Flights with a price less than or equal to: " + amount + ".", false);

			for (WebElement currentElement : elements) {
				n++;
				//System.out.println(n);
				String elementText = currentElement.getText();

				// Eliminate letters of price
				String price = currentElement.getText();
				price = price.replace(" MXN", "");
				price = price.replace("$", "");
				price = price.replace(",", "");
				int priceConverToInt = Integer.parseInt(price);// Convert to int

				if (n > 7) { // Ignore up to 7 because elements before 7 are not corresponding to prices
					if (priceConverToInt <= amount) { // Compare the prices with the maximum quantity

						//System.out.println(elementText);
						Reports.fnLog(Status.PASS, "- Flight found!! departure time: " + hour[cn] + ".", false);
						Reports.fnLog(Status.INFO, "- Regular price: " + elementText + ".", false);

						if (priceConverToInt < maxAmount) {
							maxAmount = priceConverToInt;
							recommendedFlight = hour[cn];
							bestFlightLocator = elementText;
						}

					}
					cn++;
				}
			}
			System.out.println("- cheapest recommended second flight: " + recommendedFlight + ".");
			Reports.fnLog(Status.PASS, "- Cheapest recommended flight: " + recommendedFlight + ".", false);// Show the
																											// Cheapest
																											// flight

		} catch (Exception objException) {
			System.out.println("The element was (" + locator + ") or ( " + locatorTwo + ") not located in the page.");
			Reports.fnLog(Status.FAIL, "- The Web Element: (" + locator + ") or ( " + locatorTwo + ") was not found.",
					false);
			Assert.fail();
		}
		bestFlightLocator = bestFlightLocator.replace("MXN", "");
		bestFlightLocator = "//span[text()='" + bestFlightLocator + "']/following-sibling::mat-icon"; //Create locator string
		return bestFlightLocator; // Returns the xpath of the lowest price

	}
	
	
	
	/**
	 * Price comparison of Web Elements
	 * @param locator
	 * @param expected
	 */
	public void priceComparison(By locator, int expected) {
		try 
		{
			String Actual = driver.findElement(locator).getText();
			Actual = Actual.replace(" MXN", "");
			Actual = Actual.replace("$", "");
			Actual = Actual.replace(",", "");
			int priceConverToInt = Integer.parseInt(Actual); // Convert to int
			
			if (priceConverToInt <= expected) {
				System.out.println("Price: ( "+ priceConverToInt + " ) budget: (" + expected + " ) is ok");
				Reports.fnLog(Status.PASS, "Price: ( "+ priceConverToInt + " ) budget: (" + expected + " ) is ok", true);
			} else {
				System.out.println("Price: ( "+ priceConverToInt + " ) budget: (" + expected + " ) goes out of budget");
				Reports.fnLog(Status.FAIL, "Price: ( "+ priceConverToInt + " ) budget: (" + expected + " ) goes out of budget", true);
				Assert.fail();
			}
		} 
		catch (Exception objException) {
			System.out.println("assertEquals fail");
			Reports.fnLog(Status.FAIL, "assertEquals fail", false);
			Assert.fail();
		}

	}

}
