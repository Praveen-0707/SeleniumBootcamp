package week2.day1;
import java.util.List;
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

public class DeleteLeadFromCampaign {

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
		String lead = "Test SampleXPA";		// input data
		String campName = "BootCamp";
//		String Flag_Validation = null;
		
		driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Sales");	

		WebElement Sales = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()]")));
		Sales.click();
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Leads']")));
		js.executeScript("arguments[0].click();", ele);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'search')]")));
		ele.sendKeys(lead);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='" +lead+ "']/following::td//a[@role='button'])[1]")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Delete']")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Campaigns']")));
		js.executeScript("arguments[0].click();", ele);
			
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'search')]")));
		search.sendKeys(campName,Keys.ENTER);
		search.click();
		Thread.sleep(2000);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Campaign Members']/../../../../../following-sibling::div//span[text()='View All']")));
		ele.click();
//		System.out.println("List");
		List<WebElement> listofLeads = driver.findElements(By.xpath("(//a[@title='Lead']/../../following-sibling::td//a[contains(@class,'forceOutputLookup')])"));
		int count = listofLeads.size();
		int i = 0;
		for (WebElement elements : listofLeads)
		{
			String leadMembers = elements.getText();
			if (leadMembers.equals(lead))
			{
				System.out.println("Unable to Delete Lead, TC-Failed");
				break;
			}
			else
			{
				i = i + 1;
				if (i == count)
				{
					System.out.println("Delete Lead was successful, TC-Passed");
				}
			}
			
		}
	}

}
