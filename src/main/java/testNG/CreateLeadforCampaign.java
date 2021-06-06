package testNG;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class CreateLeadforCampaign extends TestNGBaseClass {
	
	@Test
	public void createLeadforCampaign() throws InterruptedException {
		
		WebElement ele;
		JavascriptExecutor js = (JavascriptExecutor)driver;		// Java Script executor to handle WebElements
		
//		SalesForce Application
		String campName = "BootCamp";		// input data
		String Flag_Validation = null;
		String randomString = "";
		String fName = "Test";
		String lName = "Sample";
		String leadName = "";
		
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
		
		lName += randomString;
		leadName = fName + " " + lName;
		
//		Clicks on Toggle Menu
		WebElement menuClk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
		menuClk.click();
		
//		Clicks on View All and Filters search with Sales
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Sales");	

		WebElement Sales = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()]")));
		Sales.click();
		
//		Clicks on Campaigns Tab
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Campaigns']")));
		js.executeScript("arguments[0].click();", ele);
		
//		Searching for BootCamp
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'search')]")));
		search.sendKeys(campName,Keys.ENTER);
		Thread.sleep(2000);
		
//		Expands selected BootCamp
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]")));
		ele.click();
		
//		Adding Lead to BootCamp
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Add Leads']")));
		ele.click();
		
//		Selecting Value from DropDown
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//lightning-icon[contains(@class,'inputLookupIcon')]")));
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(2000);
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@title='New Lead']")));
		ele.click();
		
//		Input Values
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Salutation']/following::div/a[contains(text(),'None')])[1]")));
		ele.click();
		ele.sendKeys("M");
		ele.sendKeys(Keys.ENTER);
				
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='First Name']/following::input[contains(@class,'firstName')]")));
		ele.sendKeys(fName);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='Last Name']/following::input[contains(@class,'lastName')]")));
		ele.sendKeys(lName);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label/span[text()='Company']/following::input[contains(@class,'input')])[1]")));
		ele.sendKeys("Testleaf");
		
//		Saving Changes
		driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		Thread.sleep(1000);
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Next']")));
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(1000);
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Submit']")));
		js.executeScript("arguments[0].click();", ele);
		
//		Output validation
		WebElement output = driver.findElement(By.xpath("//div[contains(@class,'toastTitle')]"));
		wait.until(ExpectedConditions.visibilityOf(output));
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
			ele.sendKeys(leadName);
			ele.sendKeys(Keys.ENTER);
			
			ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[contains(@class,'forceOutputLookup')])[1]")));
			String val = ele.getText();
			if (val.contains(leadName))
			{
				System.out.println("TC-Passed");
			}
		}
	}

}
