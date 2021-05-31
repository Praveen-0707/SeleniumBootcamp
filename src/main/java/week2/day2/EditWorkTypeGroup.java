package week2.day2;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EditWorkTypeGroup {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
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
		String workTypeGroup_Name = "Praveen Raj_MNP";		// input data
		String descr = "Automation";
		
		driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]").click();
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Work Type Groups");	
		
//		navigates to WorkTypeGroups Tab
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p/mark[text()='Work Type Groups']")));
		ele.click();
		
//		search for WorkType
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'WorkTypeGroup-search-input')]")));
		ele.sendKeys(workTypeGroup_Name);
		Thread.sleep(2000);
		
//		selecting drop down value as edit
		ele = driver.findElementByXPath("(//a[text()='" + workTypeGroup_Name + "'])[1]//following::td//a[@role='button']");
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Edit']/..")));
		ele.click();
		
//		fields to edit
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='Description']/following::textarea")));
		ele.sendKeys(descr);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Group Type']/following::a[@class='select' and contains(text(),'Default')]")));
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(1000);
		ele.sendKeys("c",Keys.ENTER);
		
//		Saving changes
		driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		Thread.sleep(2000);
		
//		selecting WorkTypegroup
		ele = driver.findElementByXPath("(//a[text()='" + workTypeGroup_Name + "'])[1]");
		ele.click();
	
//		output validation - Description field
		WebElement output = driver.findElement(By.xpath("(//span[text()='Description'])/following::div//span[text()='Description']/following::span[@class='uiOutputTextArea']"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
		
		if (outputValue.contains(descr))
		{
			System.out.println("Description updated as: "+descr);
			System.out.println("Work Type Group - " + outputValue + " was updated successfully" + ", Passed");
		}
		else
		{
			System.out.println("Unable to update Work Type Group" + ", Failed");
		}
	}

}
