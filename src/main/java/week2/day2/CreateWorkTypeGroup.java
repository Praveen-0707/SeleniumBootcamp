package week2.day2;
import java.util.Random;
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

public class CreateWorkTypeGroup {

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
		WebElement ele;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
//		Login Page
		driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
		driver.findElement(By.id("Login")).click();
		
//		SaleForce Application
		String workTypeGroup_Name = "Prakash Raj";		// input data
		String randomString = "";
		
//		Generating Random String
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int length = 3;
		Random random = new Random();
		char[] text = new char[length];
		for (int i=0; i<length; i++)
		{
			text[i] = characters.charAt(random.nextInt(characters.length()));
			randomString += text[i];
		}
		
		workTypeGroup_Name = workTypeGroup_Name + "_" + randomString;
		
		driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Work Type Groups");
		
//		navigates to WorkTypeGroups Tab
		WebElement WTG = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p/mark[text()='Work Type Groups']")));
		WTG.click();
		
//		selecting drop down value as New Work Type Group
		WebElement WTG_DD = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[contains(@title,'Work Type Groups')]/following::div[contains(@class,'context-bar')][1]")));
		WTG_DD.click();
		Thread.sleep(2000);
		
		ele = driver.findElementByXPath("//span[text()='New Work Type Group']/ancestor::a[@role='menuitemcheckbox']");
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(2000);
		
//		Passing Input - WTG Name
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Work Type Group Name'])[2]/../following-sibling::input")));
		ele.sendKeys(workTypeGroup_Name);
		
//		Saving new WTG changes
		driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		Thread.sleep(2000);
		
//		Output validation
		WebElement output = driver.findElement(By.xpath("(//span[text()='Description'])[1]/following::div//span[@class='uiOutputText']"));
		wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
		String outputValue = output.getText();
		
		if (outputValue.contains(workTypeGroup_Name))
		{
			System.out.println("Work Type Group - " + outputValue + " was created" + ", Passed");
		}
		else
		{
			System.out.println("Unable to create Work Type Group" + ", Failed");
		}
	}

}
