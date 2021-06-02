package week1.day1;
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

public class CreateAccount {

	public static void main(String[] args) throws InterruptedException {
		
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
		
//		Login Page
		driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
		driver.findElement(By.id("Login")).click();
		
//		SaleForce Application -- Create Account Case
		String AccountName = "Testleaf Create";		// input data
		
//		Clicks on View All from Toggle Menu
		driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
		
//		Filters search with Accounts
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Accounts");	
		
		WebElement Accounts = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Accounts')]")));
		Accounts.click();
		
//		Creating New Account
		driver.findElementByXPath("//div[text()='New']").click();
		driver.findElementByXPath("//input[@name='Name']").sendKeys(AccountName);
		
//		Ownership - DD value
		WebElement dd_val = driver.findElement(By.xpath("//label[text()='Ownership']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_val));
		dd_val.click();
		dd_val.sendKeys("P");
		dd_val.sendKeys(Keys.ENTER);
		driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']").click();
		
//		Output validation
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account')]//a"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
		
		if (outputValue.contains(AccountName))
		{
			System.out.print("Account " + outputValue + " was created" + ", Passed");
		}
		else
		{
			System.out.print("Unable to create Account" + ", Failed");
		}
	}

}
