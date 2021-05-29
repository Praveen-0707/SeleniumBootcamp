package week2.day1;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLeadforCampaign {

	public static void main(String[] args) throws InterruptedException {
//		WebDriverManager.chromedriver().setup();	//not part of selenium, imported from third party jar
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options); 		//extends ChromiumDriver which extends RemoteWebDriver
        driver.manage().window().maximize();
        
        @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 20);		// doubt - should check
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);		// check whether this needs to be repeated
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		
		String url = "https://login.salesforce.com/";
		driver.get(url);
		WebElement ele;
		JavascriptExecutor js = (JavascriptExecutor)driver;		// Java Script executor to handle WebElements
		
		//Login Page
		driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
		driver.findElement(By.id("Login")).click();
		
//		Saleforce Application -- Create New Order
		String campName = "BootCamp";		// input data
		String Flag_Validation = null;
		
		driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Sales");	

		WebElement Sales = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()]")));
		Sales.click();
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Campaigns']")));
		js.executeScript("arguments[0].click();", ele);
			
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'search')]")));
		search.sendKeys("BootCamp");
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Add Leads']")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@title='New Lead']")));
		ele.click();
					
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Salutation']/following::div/a[contains(text(),'None')])[1]")));
		ele.click();
		ele.sendKeys("M");
		ele.sendKeys(Keys.ENTER);
				
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='First Name']/following::input[contains(@class,'firstName')]")));
		ele.sendKeys("Reig");
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='Last Name']/following::input[contains(@class,'lastName')]")));
		ele.sendKeys("Mclan");
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label/span[text()='Company']/following::input[contains(@class,'input')])[1]")));
		ele.sendKeys("Testleaf");
		
		driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Next']")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Submit']")));
		ele.click();
		
		//output validation
		WebElement output = driver.findElement(By.xpath("//div[contains(@class,'toastTitle')]"));
		wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
		String outputValue = output.getText();
		
		if (outputValue.contains(campName))
		{
			System.out.println(outputValue);
			Flag_Validation = "True";
		}
		else
		{
			System.out.println("Unable to update "+ campName + ", Failed");
		}
		
		if (Flag_Validation == "True")
		{
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Leads']")));
			js.executeScript("arguments[0].click();", ele);
			
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'search')]")));
			ele.sendKeys("Mclan");
			ele.sendKeys(Keys.ENTER);
			
			ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[contains(@class,'forceOutputLookup')])[1]")));
			String val = ele.getText();
			if (val.contains("Mclan"))
			{
				System.out.println("TC-Passed");
			}
		}
	}

}
