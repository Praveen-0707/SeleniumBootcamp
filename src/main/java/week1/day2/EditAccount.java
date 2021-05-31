package week1.day2;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EditAccount {

		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			@SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver, 20);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			
			String url = "https://login.salesforce.com/";
			driver.get(url);
			
//			Login Page
			driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
			driver.findElement(By.id("Login")).click();
			
//			SaleForce Application -- Create Account Case
			String AccountName = "PraveenRaj";		// input data
			String phNum = "9874563216";
			String outputValueSplit = null;
			String outputphno = null;
			
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
			Thread.sleep(2000);

//			Selecting drop down value as Edit
			WebElement AccVal = driver.findElementByXPath("(//a[text()='" + AccountName + "'])[1]//following::td//a[@role='button']");
			AccVal.click();
			WebElement selEdit = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Edit']/..")));
			selEdit.click();
			
//			Editing Account Values
			driver.findElementByXPath("//input[@name='Phone']").sendKeys(phNum);
			
//			Type - DD value
			WebElement dd_Type = driver.findElement(By.xpath("//label[text()='Type']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			wait.until(ExpectedConditions.elementToBeClickable(dd_Type));
			dd_Type.click();
			dd_Type.sendKeys("T");
			dd_Type.sendKeys(Keys.ENTER);
			
//			Industry - DD value
			WebElement dd_Industry = driver.findElement(By.xpath("//label[text()='Industry']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			wait.until(ExpectedConditions.elementToBeClickable(dd_Industry));
			dd_Industry.click();
			dd_Industry.sendKeys("H");
			dd_Industry.sendKeys(Keys.ENTER);
			
			driver.findElementByXPath("//label[text()='Billing Street']/following-sibling::div/textarea").sendKeys("Billing Address data");
			driver.findElementByXPath("//label[text()='Shipping Street']/following-sibling::div/textarea").sendKeys("Shipping Address data");
			
//			Customer Priority - DD value
			WebElement dd_custPriority = driver.findElement(By.xpath("//label[text()='Customer Priority']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			wait.until(ExpectedConditions.elementToBeClickable(dd_custPriority));
			dd_custPriority.click();
			dd_custPriority.sendKeys("L"+Keys.ENTER);
				
//			SLA - DD value
			WebElement dd_SLA = driver.findElement(By.xpath("//label[text()='SLA']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			wait.until(ExpectedConditions.elementToBeClickable(dd_SLA));
			dd_SLA.click();
			dd_SLA.sendKeys("S"+Keys.ENTER);
//			dd_SLA.sendKeys(Keys.ENTER);
			
//			Upsell Opportunity - DD value
			WebElement dd_upsellOpp = driver.findElement(By.xpath("//label[text()='Upsell Opportunity']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			wait.until(ExpectedConditions.elementToBeClickable(dd_upsellOpp));
			dd_upsellOpp.click();
			dd_upsellOpp.sendKeys("N"+Keys.ENTER);
			
//			Active - DD value
			WebElement dd_Active = driver.findElement(By.xpath("//label[text()='Active']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			wait.until(ExpectedConditions.elementToBeClickable(dd_Active));
			dd_Active.click();
			dd_Active.sendKeys("N"+Keys.ENTER);
			
			driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']").click();
			
//			Output validation
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
			String outputValue = output.getText();

			if (outputValue.contains(AccountName))
			{
				System.out.println(outputValue);
				Thread.sleep(3000);
				WebElement editedVal = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='" + AccountName + "'])[1]//following::td[2]//span[contains(@class,'forceOutputPhone')]")));
				String editedValue = editedVal.getText();
				System.out.println(editedValue);
				outputValueSplit = editedValue.split("\\-")[0];
				outputValueSplit = outputValueSplit.replace("(", "");
				outputValueSplit = outputValueSplit.replace(")", "");
				outputValueSplit = outputValueSplit.replace(" ", "");
				outputphno = outputValueSplit + editedValue.split("\\-")[1];
				System.out.println(outputphno);
				if (phNum.equals(outputphno))
				{
					System.out.println("Phone No validated" + ", Passed");
				}
				else
				{
					System.out.print("Unable to validate Phone No" + ", Failed");
				}
			}
			else
			{
				System.out.print("Unable to create Account" + ", Failed");
			}
		}
	}
