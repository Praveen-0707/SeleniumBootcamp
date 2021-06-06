package testNG;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class DeleteWorkTypeGroup extends TestNGBaseClass {
	
	@Test
	public void deleteWorkTypeGroup() throws InterruptedException {

		String url = "https://login.salesforce.com/";
		driver.get(url);
		WebElement ele;
		
//		Login Page
		driver.findElement(By.id("username")).sendKeys("cypress@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Selbootcamp@123");
		driver.findElement(By.id("Login")).click();
		
//		SaleForce Application
		String workTypeGroup_Name = "Prakash Raj_UJO";		// input data
		
//		Clicks on Toggle Menu View All from Toggle Menu
		WebElement menuClk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
		menuClk.click();
		
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
		
//		selecting drop down value as Delete
		ele = driver.findElementByXPath("(//a[text()='" + workTypeGroup_Name + "'])[1]//following::td//a[@role='button']");
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		ele.click();
		
//		Delete PopUp
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Delete']")));
		ele.click();
		
//		Clearing Search Values
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'WorkTypeGroup-search-input')]")));
		ele.clear();
		
//		Refreshing the Table
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@name='refreshButton']")));
		ele.click();
		Thread.sleep(3000);
		
//		Search for Deleted WTG - Output Validation
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr"));
		int size = rows.size();
		for (int i = 1; i <= size; i++)
		{
			WebElement listofWTG_Names = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr["+i+"]/th//a"));
			String WTG_Names = listofWTG_Names.getText();
		
			if (WTG_Names.equals(workTypeGroup_Name))
			{
				System.out.println("Unable to Delete WorkTypeGroup, TC-Failed");
				break;
			}
			else
			{
				if (i == size)
				{
					System.out.println("Delete WorkTypeGroup was successful, TC-Passed");
				}
			}
			
		}
	}
}
