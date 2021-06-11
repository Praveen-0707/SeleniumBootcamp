package leafground.page.webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingAutoCompleteSearchBox {

public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on AutoComplete Image
		WebElement imgautoComplete = driver.findElementByXPath("//h5[text()='Auto Complete']/following-sibling::img");
		imgautoComplete.click();
		
		driver.findElementByClassName("ui-autocomplete-input").sendKeys("Se");
		
		List<WebElement> suggestions = driver.findElementsByXPath("//ul[@id='ui-id-1']/li/descendant::div[@class='ui-menu-item-wrapper']");
		int size = suggestions.size();
		for (int i=0; i<size; i++)
		{
			String value = suggestions.get(i).getText();
			if (value.contains("Web Service"))
			{
				suggestions.get(i).click();
			}
		}
	}

}
