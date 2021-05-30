package week2.day1;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingAlerts {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().minimize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		String url = "http://leafground.com/pages/Alert.html";
		driver.get(url);
		
//		LeafGround WebPage - Alerts
		
//		Simple Alerts - Used to notify a warning message with 'OK' button
		driver.findElementByXPath("//button[text()='Alert Box']").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		
//		Confirmation Alerts - used for the user confirmation of tasks
		driver.findElementByXPath("//button[text()='Confirm Box']").click();
		alert.dismiss();
		Thread.sleep(2000);
		
//		Prompt Alert - asks user to input the required information to complete the task
		driver.findElementByXPath("//button[text()='Prompt Box']").click();
		alert.sendKeys("Testing Sample Alert");
		alert.accept();
		Thread.sleep(2000);
		
//		Kind of Simple Alerts - displays more than a single line of information to User
		driver.findElementByXPath("//button[text()='Line Breaks?']").click();
		String alertText = alert.getText();
		System.out.println("Text from ALert: "+alertText);
		Thread.sleep(2000);
		
//		Sweet Alert - a PopUp window but cannot be handled with default ALert methods
		driver.findElementByXPath("//button[text()='Sweet Alert']").click();
		alert.accept();		//UnhandledAlertException - Since it is a PopUp dialog not an Alert
		
	}

}

/*
What is Alerts
- An Alert in Selenium is a small message box which appears on screen to give the user some information or notification. It notifies the user with some specific information or error, asks for permission to perform certain tasks and it also provides warning messages as well
- An Alert requires user actions
- Alert() returns an object of class RemoteAlert which implements the Alert interface.
 
 Difference between Alerts and Popup
 Alerts- Cant able to inspect and get webelement
 popup-can able to inspect and get webelement
  
  
 *Types of Alert:
 Simple Alert-->only OK button (can able to accept or reject alert)
 Prompt Alert-->ok and cancel button and text box (can able to pass values, able to accept or reject alert)
 Confirmation Alert-->only ok and cancel button (can able to accept and reject)


*Methods available in Alerts
accept();
dismiss();
sendKeys();
getText();


*Handle Alerts
accept(); ------> To approve the alert
dismiss();------> To reject the alert
sendKeys();-----> To pass values in alert 
getText();------> To get text present in alert

*Exception in Alert
Noalertpresent-> If no alert is present we will get this exception
Unhandledalertexception-> occurs when alert it is not addressed ex: if alert present in screen, but we are trying to perform 
							some other action without acknowledging alert will throw UnhandledAlertException
*/