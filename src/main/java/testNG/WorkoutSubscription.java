package testNG;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class WorkoutSubscription extends TestNGBaseClass {
	
	@Test
	public void createNewSubscription() throws InterruptedException {
		
		WebElement ele;
		String DashboardName = "PraveenRaj_Workout";
		String Descr = "Testing";
		boolean flag_sort = false, flag_deleteValidation = false;
		String sortMsg;
		
		
//		Clicks on Toggle Menu View All from Toggle Menu
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
		ele.click();
		
//		Clicks on View All and Filters search with Service Console
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		ele.click();
		driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']").sendKeys("Service Console");	

		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p/mark[text()='Service Console']")));
		ele.click();
		Thread.sleep(5000);
		
//		Clicks on Home from DropDown Menu 
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Show Navigation Menu']")));
		ele.click();
		Thread.sleep(1000);
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@title='Home'])[last()]")));
		ele.click();

//		Fetching Close and Open Values and adding them
		WebElement ClosePriceTag = driver.findElementByXPath("//span[text()='Closed']/following-sibling::span[contains(text(),'$')]");
		String ClosePrice = ClosePriceTag.getText();
		ClosePrice = ClosePrice.replaceAll("\\D", "");
		int ClosePriceIntValue = Integer.parseInt(ClosePrice); 
		
		WebElement OpenPriceTag = driver.findElementByXPath("//span[contains(text(),'Open')]/following-sibling::span[contains(text(),'$')]");
		String OpenPrice = OpenPriceTag.getText();
		OpenPrice = OpenPrice.replaceAll("\\D", "");
		int OpenPriceIntValue = Integer.parseInt(OpenPrice); 
		
		int StrikePrice = 10000;
		int Price = ClosePriceIntValue + OpenPriceIntValue;
		if (Price < StrikePrice)
		{
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Edit Goal']")));
			ele.click();
			
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@id='currencyCode']/following-sibling::input")));
			ele.clear();
			ele.sendKeys("10000");
			
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Save']/ancestor::button)[last()]")));
			ele.click();
		}
		
//		Clicks on DashBoard from DropDown Menu
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Show Navigation Menu']")));
		ele.click();
		Thread.sleep(1000);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@title='Dashboards'])[last()]")));
		ele.click();
		Thread.sleep(2000);
		
//		Creating New DashBoard
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='New Dashboard']")));
		ele.click();
		Thread.sleep(3000);
		
//		Switching to Frame to input values
		WebElement frames = driver.findElementByXPath("(//iframe[@title='dashboard'])[last()]");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frames));
		Thread.sleep(2000);
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[text()='Name']/following::div//input[@id='dashboardNameInput']")));
		ele.sendKeys(DashboardName);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@id='dashboardDescriptionInput' and @type='text']")));
		ele.sendKeys(Descr);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@id='submitBtn']")));
		ele.click();
		driver.switchTo().defaultContent();		//Switching back to Window from Frame
		Thread.sleep(3000);
		
//		Switching to Frame-1 for Clicking on Done button
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		Thread.sleep(6000);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[contains(@class,'doneEditing') and text()='Done']")));
		ele.click();
		Thread.sleep(3000);
		
//		Validating the New DashBoard title - Clicks on Subscribe
		WebElement TitleElement = driver.findElementByXPath("//div[@class='slds-page-header__name-title']//span[contains(text(),'Dashboard')]/following-sibling::span");
		wait.until(ExpectedConditions.visibilityOf(TitleElement));
		String TitleVerification = TitleElement.getText();					
		if (TitleVerification.contains(DashboardName))
		{
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='Subscribe']")));
			ele.click();
			driver.switchTo().defaultContent();
		}
		
//		Input Values
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//legend[text()='Frequency']/following::div//span[text()='Daily']")));
		ele.click();
		
//		Selecting Time from DropDown
		WebElement time_DD = driver.findElementByXPath("//select[@id='time']");
		wait.until(ExpectedConditions.visibilityOf(time_DD));
		Select time = new Select(time_DD);
		time.selectByVisibleText("10:00 AM");
		
//		Saving Changes
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Save']")));
		ele.click();
		
//		Validating if Subscription is created Successfully
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'subscription')]"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
		System.out.println(outputValue);
		
		if (outputValue.contains("You started a dashboard subscription"))
		{
//			Closes the Subscription Tab
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[contains(@title,'"+DashboardName+"') and contains(@title,'Close')]")));
			ele.click();
		}
		
//		Clicks on DashBoard tab
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Dashboards']")));
		ele.click();
		
//		Clicks on Private DashBoard link
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@title='Private Dashboards'])[1]")));
		ele.click();
		
//		Filters search with Newly created DashBoard Name
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@class,'search-text')]")));
		ele.sendKeys(DashboardName);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
//		Deletes the Subscription
		ele = driver.findElementByXPath("(//a[@title='"+DashboardName+"'])[1]//following::td[6]//button");
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
				
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Delete']/..")));
		ele.click();		
						
//		Handling Delete PopUp
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Delete']")));
		ele.click();				
		
		WebElement displayMsg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"))));
		String displayMsgValue = displayMsg.getText();
		System.out.println(displayMsgValue);
		
		if (displayMsgValue.contains("Dashboard was deleted"))
		{
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@class,'search-text')]")));
			ele.clear();
			ele.sendKeys(" ",Keys.ENTER);
			flag_sort = true;
			Thread.sleep(3000);
		}
		
//		Sorting the Table records in descending order to get new records
		if (flag_sort)
		{
			WebElement clkAccountsSort_1 = driver.findElementByXPath("//span[text()='Created On']/ancestor::a");
			wait.until(ExpectedConditions.elementToBeClickable(clkAccountsSort_1));
			clkAccountsSort_1.click();
			WebElement AccountsSort = driver.findElementByXPath("//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
			wait.until(ExpectedConditions.visibilityOf(AccountsSort));
			sortMsg = AccountsSort.getText();
			if (sortMsg.contains("Sorted Descending"))
			{
				flag_deleteValidation = true;
			}
			else
			{
				WebElement clkAccountsSort_2 = driver.findElementByXPath("//span[text()='Created On']/ancestor::a");
				wait.until(ExpectedConditions.elementToBeClickable(clkAccountsSort_2));
				clkAccountsSort_2.click();
				WebElement AccountsSort_2 = driver.findElementByXPath("//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
				wait.until(ExpectedConditions.visibilityOf(AccountsSort_2));
				sortMsg = AccountsSort_2.getText();
				if (sortMsg.contains("Sorted Descending"))
				{
					flag_deleteValidation = true;
				}
				else
				{
					System.out.println("Accounts are not sorted in order for validation" + ", Failed");
				}	
		}
		
//		Searching for Deleted Subscription - Output Validation
		if (flag_deleteValidation)
		{
			List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table_resizable-cols')]/tbody/tr"));
			int size = rows.size();
			for (int i = 1; i <= size; i++)
			{
				WebElement listofSubscriptions = driver.findElement(By.xpath("//table[contains(@class,'table_resizable-cols')]/tbody/tr["+i+"]/th//a"));
				String subscriptionNames = listofSubscriptions.getText();
			
				if (subscriptionNames.equals(DashboardName))
				{
					System.out.println("Unable to Delete Subscription, TC-Failed");
					break;
				}
				else
				{
					if (i == size)
					{
						System.out.println("Delete Subscription was successful, TC-Passed");
					}
				}
				
			}
		}				
	}
 }
}
