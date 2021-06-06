package testNG;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CreateOpportunity extends TestNGBaseClass {
	
	@Test(invocationCount = 3)
	public void createOpportunity() throws InterruptedException {

		WebElement ele;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
//		SalesForce Application
		String oppName = "TMT Steel";		// input data
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
		
		oppName = oppName + "_" + randomString;
		
//		Clicks on Toggle Menu
		WebElement menuClk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
		menuClk.click();
		
//		Clicks on View All and Filters search with Sales
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Sales");	

		WebElement Sales = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()]")));
		Sales.click();
		
//		Clicks on View All Deals
		WebElement allDeals = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a/span[text()='View All Key Deals']")));
		allDeals.click();
		Thread.sleep(2000);
		
//		Selecting DropDown from Tab Menu
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@title='Opportunities']/following::div")));
		ele.click();
		Thread.sleep(3000);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='All Opportunities']/ancestor::a[@role='menuitemcheckbox']")));
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(2000);
		
//		Creating new Opportunity
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='New']")));
		ele.click();
		Thread.sleep(2000);
		
//		Input Values
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
		
//		Output validation
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
