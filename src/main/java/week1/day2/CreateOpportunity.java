package week1.day2;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunity {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 20);		// doubt - should check
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);		// check whether this needs to be repeated
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		
		String url = "https://login.salesforce.com/";
		driver.get(url);
		WebElement ele;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//Login Page
		driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
		driver.findElement(By.id("Login")).click();
		
//		Saleforce Application -- Create Account Case
		String oppName = "TMT Steel";		// input data
		driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Sales");	

		WebElement Sales = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()]")));
		Sales.click();
		
		WebElement allDeals = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a/span[text()='View All Key Deals']")));
		allDeals.click();
		
		Thread.sleep(2000);
		WebElement Opp = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@title='Opportunities']/following::div")));
		Opp.click();
		
		Thread.sleep(3000);
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='All Opportunities']/ancestor::a[@role='menuitemcheckbox']")));
		js.executeScript("arguments[0].click();", ele);
		
		Thread.sleep(2000);
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='New']")));
		ele.click();
//		driver.findElementByXPath("//div[text()='New']").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//label[text()='Opportunity Name']/following-sibling::div//input[@name ='Name']").sendKeys(oppName);
		driver.findElementByXPath("//label[text()='Amount']/following-sibling::div//input[@name ='Amount']").sendKeys("60000");
		driver.findElementByXPath("//label[text()='Close Date']/following-sibling::div//input[@name ='CloseDate']").sendKeys("6/10/2021");
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Type']/following-sibling::div//input[@type ='text']")));
		ele.click();
		ele.sendKeys("N");
		ele.sendKeys(Keys.ENTER);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Lead Source']/following-sibling::div//input[@type ='text']")));
		ele.click();
		ele.sendKeys("P");
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Stage']/following-sibling::div//input[@type ='text']")));
		ele.click();
		ele.sendKeys("N");
		ele.sendKeys(Keys.ENTER);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Primary Campaign Source']/following-sibling::div//input[@type='text']")));
		ele.click();
		Thread.sleep(1000);
		ele.sendKeys(Keys.ARROW_DOWN);
		ele.sendKeys(Keys.ENTER);
		
		driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']").click();
		
		//output validation
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Opportunity')]//a"));
		wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
		String outputValue = output.getText();
		
		if (outputValue.contains(oppName))
		{
			System.out.println("Stage 1 Passed");
			ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[contains(text(),'Opportunity')]/following-sibling::slot/slot[@slot='primaryField']")));
			String val = ele.getText();
			if (val.contains(oppName))
			System.out.println("Opportunity " + outputValue + " was created" + ", Passed");
		}
		else
		{
			System.out.println("Unable to create Account" + ", Failed");
		}
	}

}
