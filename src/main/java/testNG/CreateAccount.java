package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


public class CreateAccount extends TestNGBaseClass {
	
	@Test
	public void createAcc() throws InterruptedException {
		
//		SalesForce Application -- Create Account Case
		String AccountName = "ReigCC";		// input data
		
//		Clicks on Toggle Menu View All from Toggle Menu
		WebElement menuClk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
		menuClk.click();
		
//		Clicks on View All and Filters search with Accounts
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
