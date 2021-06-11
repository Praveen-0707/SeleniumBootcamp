package leafground.page.webelements;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingWindows {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on Image Window
		WebElement imgWindow = driver.findElementByXPath("//h5[text()='Window']/following-sibling::img");
		imgWindow.click();
		
//		Handling Windows
		driver.findElementByXPath("//button[text()='Open Multiple Windows']").click();
		String mainWindow = driver.getWindowHandle();
		
		Set<String> childWindows = driver.getWindowHandles();
		Iterator<String> iterator = childWindows.iterator();
		while(iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!(mainWindow.equalsIgnoreCase(childWindow)))
			{
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
		System.out.println(driver.getTitle());
	}

}
