package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class DeleteAccount extends TestNGBaseClass {
		
		@Test(dependsOnMethods = {"testNG.CreateAccount.createAcc","testNG.EditAccount.editAcc"})
		public void deleteAcc() throws InterruptedException {
			
//			SalesForce Application
			String AccountName = "ReigCC";		// input data

//			Clicks on Toggle Menu
			WebElement menuClk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
			menuClk.click();
			
//			Clicks on View All and Filters search with Accounts
			WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
			viewALL.click();
			driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Accounts");
			
			WebElement Accounts = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Accounts')]")));
			Accounts.click();
			Thread.sleep(3000);
			
//			Account Search
			WebElement searchAcc = driver.findElementByXPath("//input[@name='Account-search-input']");
			wait.until(ExpectedConditions.elementToBeClickable(searchAcc));
			searchAcc.sendKeys(AccountName);
			searchAcc.sendKeys(Keys.ENTER);
			Thread.sleep(3000);

//			Selecting drop down value as Delete
			WebElement AccVal = driver.findElementByXPath("(//a[text()='" + AccountName + "'])[1]//following::td//a[@role='button']");
			AccVal.click();
			WebElement selDelete = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
			selDelete.click();
			
//			Delete PopUp
			WebElement delconfirmation = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button//span[text()='Delete']")));
			delconfirmation.click();
			
			//Output validation
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
			String outputValue = output.getText();
			if (outputValue.contains(AccountName))
			{
				outputValue = outputValue.split("\\.")[0];
				System.out.print(outputValue + ", Passed");
			}
			else
			{
				System.out.print("Unable to create Account" + ", Failed");
			}
		}
	}
