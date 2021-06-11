package leafground.page.webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingDropdowns {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on DropDown Image
		WebElement imgDropdown = driver.findElementByXPath("//h5[text()='Drop down']/following-sibling::img");
		imgDropdown.click();
		
//		SelectsBy - ID
		Select selectByIndex = new Select(driver.findElementById("dropdown1"));
		WebElement firstSelectedOption = selectByIndex.getFirstSelectedOption();
		System.out.println(firstSelectedOption.getText());			// prints the default selected value
		selectByIndex.selectByIndex(1);
		
//		SelectsBy - Text
		Select selectByText = new Select(driver.findElementByName("dropdown2"));
		selectByText.selectByVisibleText("UFT/QTP");
		
//		SelectsBy - Value
		Select selectByValue = new Select(driver.findElementById("dropdown3"));
		selectByValue.selectByValue("4");
		
//		Gets total options count
		Select listCount = new Select(driver.findElementByClassName("dropdown"));
		List<WebElement> options = listCount.getOptions();
		System.out.println(options.size());
		
//		Passing values using SendKeys
		WebElement selectBySendkeys = driver.findElementByXPath("//*[@id='contentblock']//div[5]/select");
		selectBySendkeys.sendKeys("UFT/QTP");
		
//		MultiSelect DropDown
		Select selectMultipleOptions = new Select(driver.findElementByXPath("//*[@id='contentblock']//div[6]/select"));
		selectMultipleOptions.selectByIndex(1);
		selectMultipleOptions.selectByVisibleText("UFT/QTP");
		selectMultipleOptions.selectByValue("4");
	}

}
