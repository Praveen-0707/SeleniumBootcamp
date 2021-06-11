package leafground.page.webelements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingCalendars {
	
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static void main(String[] args) throws ParseException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		HandlingCalendars calendars = new HandlingCalendars();
		calendars.selectCalendarTypeOne("30-Aug-2021");
		calendars.selectCalendarTypeTwo("30-Oct-2021");
		calendars.selectCalendarTypeThree("10-19-2021"); //MMDDYYYY
	
	}
	
	//	******Calendar Type-1******
		void selectCalendarTypeOne(String dateToSelect) throws InterruptedException
		{
			String URL = "http://leafground.com";
			driver.get(URL);
			
	//		Clicks on Drag Window by offset
			WebElement imgCalendar = driver.findElementByXPath("//h5[text()='Calendar']/following-sibling::img");
			imgCalendar.click();
	
//			String dateToSelect = "07-07-2021";
			
			String[] dateVal = dateToSelect.split("-");
			
	//		Removes first char from date if StartsWith 0
			String date = dateVal[0];
			boolean startsWithZero = date.startsWith("0");
			if (startsWithZero) { date = date.substring(1); }
			
	//		Clicks on DatePicker control
			driver.findElementById("datepicker").click();
			
			int monthToSelect = Integer.parseInt(dateVal[1]);
			
	//		Convert the Current Month name to Month number
			SimpleDateFormat outputformat = new SimpleDateFormat("MM");
			Calendar cal = Calendar.getInstance();
			int presentMonth = Integer.parseInt(outputformat.format(cal.getTime()));
			
			if (monthToSelect > presentMonth)
			{
				for (int i=0; i < monthToSelect - presentMonth; i++)
				{
					driver.findElementByXPath("//span[text()='Next']").click();
					Thread.sleep(2000);
				}
			}
			else if (presentMonth > monthToSelect)
			{
				for (int i=0; i < presentMonth - monthToSelect; i++)
				{
					driver.findElementByXPath("//span[text()='Prev']").click();
					Thread.sleep(2000);
				}
			}
	//		Selects date
			driver.findElementByLinkText(date).click();
			
		}
	
	
		//	******Calendar Type-2******
		void selectCalendarTypeTwo(String date) throws InterruptedException
		{
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			String URL = "https://jqueryui.com";
			driver.get(URL);
			
			driver.findElementByLinkText("Datepicker").click();
			driver.findElementByLinkText("Display month & year menus").click();
			driver.switchTo().frame(0);
			
//			String date = "30-Oct-2021";
			String dateArr[] = date.split("-");
			String day = dateArr[0];
			String month = dateArr[1];
			String year = dateArr[2];
			
			WebElement calClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("datepicker")));
			calClick.click();
			Thread.sleep(2000);
			
			WebElement selectMonth = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@class='ui-datepicker-month']")));
			Select select = new Select(selectMonth);
			select.selectByVisibleText(month);
			
			WebElement selectYear = wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("ui-datepicker-year")));
			Select select1 = new Select(selectYear);
			select1.selectByVisibleText(year);
			
			String beforeXpath = "//table[@class='ui-datepicker-calendar']//tbody/tr[";
			String afterXpath = "]/td[";
			
			final int totalWeekDays = 7;
			
			boolean flag = false;
			String dayVal = null;
			for(int rowNum=1; rowNum<=5; rowNum++){
				
				for(int colNum = 1; colNum<=totalWeekDays; colNum++){
					try{
				    dayVal =driver.findElement(By.xpath(beforeXpath+rowNum+afterXpath+colNum+"]")).getText();
					}catch (NoSuchElementException e){
						System.out.println("Please enter a correct date value");
						flag = false;
						break;
					}
	//				System.out.println(dayVal);
					if(dayVal.equals(day)){
						driver.findElement(By.xpath(beforeXpath+rowNum+afterXpath+colNum+"]")).click();
						flag = true;
						break;
					}				
				}
				if(flag){
					break;
				}
			}
		}
		
//		******Calendar Type-3 by JavaScript******
			void selectCalendarTypeThree(String dateVal) throws InterruptedException
			{
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				
				String URL = "https://jqueryui.com";
				driver.get(URL);
				
				driver.findElementByLinkText("Datepicker").click();
				driver.findElementByLinkText("Display multiple months").click();
				driver.switchTo().frame(0);
				
				WebElement calClick = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("datepicker")));
				calClick.click();
				js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", calClick);
				Thread.sleep(2000);
				calClick.sendKeys(Keys.ENTER);
//				calClick.sendKeys(Keys.RETURN);
			}						
			
}