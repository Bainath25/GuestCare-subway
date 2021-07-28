

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Product_Suggestion_Base {
	/*
	 * 
	 * This are Xpaths
	 * 
	 */
	static String Menu_Inquirey="//input[@id='v2main_0_insetcontainer_6cfebaeb54424adc88c25050d0487f87_0_btnMenuOther']";
	static String Product_Suggestion="//input[@id='v2main_0_insetcontainer_6cfebaeb54424adc88c25050d0487f87_0_btnMenuInquiries_Ps']";
	static String URL="https://swqe.test.subway.com/en-US/ContactUs/GuestCare";
	static String Prod_URL="https://www.subway.com/en-us/contactus/guestcare";
	static String fr_ca_URL="https://swqe.test.subway.com/fr-ca/ContactUs/guestcare";
	static String First_name="//input[@id='txtFirstName']";
	static String Last_name="//input[@id='txtLastName']";
	static String Email="//input[@id='txtEmail']";
	static String Confirm_email="//input[@id='txtConfirmEmail']";
	static String Phone_number="//input[@id='v2main_0_insetcontainer_6cfebaeb54424adc88c25050d0487f87_0_txtPhoneNumber']";
	static String Restaurant_number="//input[@id='storeNumber']";
	static String Satellite_number="//input[@id='satelliteNumber']";
	static String Description="//textarea[@id='cs_comments']";
	static String subway_version="//input[@id='txtSubwayAppVersion']";				
	static String Submit="btnSubmit";
	static String subway_toggle="//label[@class='guestcare-switch checkboxSubwayAppVersion subwayAppVersion']//span[@class='guestcare-slider round']";
	static String close_btn="btnCloseMsg";
	static String popup="//button[@class=\"onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon\"]";
	static String minutes="//select[@id='ddlVisitTimeMin']";
	static String hours="//select[@id='ddlVisitTimeHr']";
	static String meridian="//select[@id='ddlVisit_ampm']";
	static String Calendar_icon="//img[@title='Select date']";
	static String Date_picker="//a[normalize-space()='1']";
	static String Transaction_ID="//input[@id='v2main_0_insetcontainer_6cfebaeb54424adc88c25050d0487f87_0_txtTransactionId']";
	static String Android="//input[@id='v2main_0_insetcontainer_6cfebaeb54424adc88c25050d0487f87_0_btnYourDigitalOrder_MobileAppAD']";
	static String Android_version="//input[@id='txtAndroidVersion']";
	static String Browser_select="//select[@id='ddlBrowserType']";
	
	/*
	 *   
	 * This is main page 
	 *   
	 */
		
	public static WebDriver driver;
	public static Scanner scn;
	public static String browser;
	public static void setproperty()
	{
		
		scn=new Scanner(System.in);
		System.out.println("Choose Below Options in which browser to open ?"+'\n'+ "1. For GoogleChrome"+'\t' +"2. For EdgeBrowser"+'\t'+ "3.For Firefox "+'\n');
		browser=scn.nextLine();
		if(browser.equals("1"))
		{
		System.out.println("You have choosen    " +"Chrome Browser");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(browser.equals("2"))
		{
		System.out.println("You have choosen    " +"Edge Browser");	
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();	
		}
		else if(browser.equals("3"))
		{
		System.out.println("You have choosen    " +"Firefox Browser");
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		}
		else {
		System.out.println("Please Choose Option 1 or 2 or 3");
		setproperty();
			
		}
		scn.close();
		
	}
	public static void scrollBy() 
	{
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,300)", "");
		jse.executeScript("scrollBy(0,400)");
	} 
	public static void Screenshot() throws IOException
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File Src=scrShot.getScreenshotAs(OutputType.FILE);
		File Dest=new File("./target/ProductSuggestion.png");
		FileUtils.copyFile(Src, Dest);
	}
	
	public static void wait(int i) throws InterruptedException
	{
		Thread.sleep(i * 1000);
	}
	public static void quit_browser()
	{
		driver.quit();
	}
	public static void TestEnv()
	{
		driver.get(URL);
	}
	public static void Execution() throws InterruptedException
	{
		setproperty();
		
		driver.get(URL);
		//driver.get(Prod_URL);
		driver.manage().window().maximize();
		driver.findElement(By.xpath(popup)).click();
		scrollBy();
		wait(2);
		
		//Select the Menu Inquiries page
		driver.findElement(By.xpath(Menu_Inquirey)).click();
		scrollBy();
		wait(2);
		driver.findElement(By.xpath(Product_Suggestion)).click();
		
	}
		
		//Filling the details in form
		public static void form_page() throws InterruptedException, IOException
		{
		
		driver.findElement(By.xpath(First_name)).sendKeys("Bainath");
		wait(1);
		driver.findElement(By.xpath(Last_name)).sendKeys("Test_01");
		wait(1);
		driver.findElement(By.xpath(Email)).sendKeys("s@s.com");
		wait(1);
		driver.findElement(By.xpath(Confirm_email)).sendKeys("s@s.com");
		wait(1);
		driver.findElement(By.xpath(Phone_number)).sendKeys("9785783742");
		wait(1);
		//scrollBy();
		
		//Select country Drop-down
		Select country=new Select(driver.findElement(By.id("ddlState")));
		country.selectByVisibleText("Alaska");
		wait(1);
		driver.findElement(By.xpath(Restaurant_number)).sendKeys("25572");
		driver.findElement(By.xpath(Satellite_number)).sendKeys("0");
		wait(1);
		
		//Date Picker
		driver.findElement(By.xpath(Calendar_icon)).click();
		wait(1);
		driver.findElement(By.xpath(Date_picker)).click();
		wait(1);
		
		//Select time Drop-down
		Select hh=new Select(driver.findElement(By.xpath(hours)));
		hh.selectByVisibleText("3");
		Select mm=new Select(driver.findElement(By.xpath(minutes)));
		mm.selectByVisibleText("15");
		wait(1);
		Select pm=new Select(driver.findElement(By.xpath(meridian)));
		pm.selectByVisibleText("PM");
		wait(1);
		driver.findElement(By.xpath(Transaction_ID)).sendKeys("Id12345#123");
		driver.findElement(By.xpath(Description)).sendKeys("Testing with Automation");
		wait(2);
		scrollBy();
		WebElement ele=driver.findElement(By.id(Submit));
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		wait(1);
		scrollBy();
		wait(2);
		Screenshot();
		driver.findElement(By.id(close_btn)).click();
		wait(2);
		System.out.println("Successfully Submitted Product Suggestion Form");
		wait(3);
		quit_browser();
		}
}


