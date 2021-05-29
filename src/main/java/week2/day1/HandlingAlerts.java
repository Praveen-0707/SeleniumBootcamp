package week2.day1;
//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
