package week1.day2;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySorting {

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver, 20);		// doubt - should check
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			
			String url = "https://login.salesforce.com/";
			driver.get(url);
			
			//Login Page
			driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
			driver.findElement(By.id("Login")).click();
			
//			Saleforce Application -- Create Account Case
			String sortMsg = null;	// input data

			driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
			
			WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
			viewALL.click();
			driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Accounts");	
			
			WebElement Accounts = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Accounts')]")));
			Accounts.click();
			Thread.sleep(9000);
			
			WebElement AccountsSorting = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/..")));
			AccountsSorting.click();
			
			WebElement AccountsSort = driver.findElementByXPath("//span[@aria-live='assertive' and contains(text(),'Sorted')]");
			wait.until(ExpectedConditions.visibilityOf(AccountsSort));
			sortMsg = AccountsSort.getText();
			System.out.println(sortMsg);
			if (sortMsg.contains("Sorted Ascending"))
			{
				System.out.println("Accounts are displayed in ascending order" + ", Passed");	
			}
			else
			{
				driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/..").click();
				wait.until(ExpectedConditions.visibilityOf(AccountsSort));
				sortMsg = AccountsSort.getText();
				if (sortMsg.contains("Sorted Ascending"))
				{
					System.out.println("Accounts are displayed in ascending order" + ", Passed");
				}
				else
				{
					System.out.println("Unable to sort Accounts" + ", Failed");
				}
				
			}
		}
	}
