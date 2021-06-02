package week1.day2;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteAccount {

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
			ChromeDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver, 20);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			
			String url = "https://login.salesforce.com/";
			driver.get(url);
			
			//Login Page
			driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
			driver.findElement(By.id("Login")).click();
			
//			SaleForce Application
			String AccountName = "Testleaf Test";		// input data
			
//			Clicks on View All from Toggle Menu
			driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
			
//			Filters search with Accounts
			WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
			viewALL.click();
			driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Accounts");	
			
			WebElement Accounts = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Accounts')]")));
			Accounts.click();
			
//			Account Search
			WebElement searchAcc = driver.findElementByXPath("//input[@name='Account-search-input']");
			searchAcc.sendKeys(AccountName);
			searchAcc.sendKeys(Keys.ENTER);
			Thread.sleep(6000);

//			Selecting drop down value as Delete
			WebElement AccVal = driver.findElementByXPath("(//a[text()='" + AccountName + "'])[1]//following::td//a[@role='button']");
			AccVal.click();
			WebElement selDelete = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
			selDelete.click();
			
//			Delete PopUp
			WebElement delconfirmation = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button//span[text()='Delete']")));
			delconfirmation.click();
			
			//Output validation
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
			String outputValue = output.getText();
			if (outputValue.contains(AccountName))
			{
				outputValue = outputValue.split("\\.")[0];
				System.out.print(outputValue + ", Passed");
			}
			else
			{
				System.out.print("Unable to create Account" + ", Failed");
			}
		}
	}
