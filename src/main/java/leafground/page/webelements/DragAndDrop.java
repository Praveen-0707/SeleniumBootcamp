package leafground.page.webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on Drag Window by offset
		WebElement imgDrag = driver.findElementByXPath("//h5[text()='Draggable']/following-sibling::img");
		imgDrag.click();
		
		Actions action = new Actions(driver);
		action.clickAndHold(driver.findElementById("draggable")).moveByOffset(100, 150).release().perform();
		driver.navigate().back();
		Thread.sleep(1000);
		
//		Clicks on Drop Window
		WebElement imgDrop = driver.findElementByXPath("//h5[text()='Droppable']/following-sibling::img");
		imgDrop.click();
		
//		clicks and drags the object element to target
		action.clickAndHold(driver.findElementById("draggable")).moveToElement(driver.findElementById("droppable")).release().perform();
		driver.navigate().back();
		Thread.sleep(1000);
		
//		Handling MouseOver and Printing the available links
		WebElement imgMouseOver = driver.findElementByXPath("//h5[text()='Mouse Hover']/following-sibling::img");
		imgMouseOver.click();
		WebElement linkText = driver.findElementByLinkText("TestLeaf Courses");
		action.moveToElement(linkText).perform();
		Thread.sleep(3000);
		
		String beforeXpathLinks = "//a[text()='TestLeaf Courses']/following::ul//li";
		String afterXpathLinks = "/a";
		List<WebElement> allLinks = driver.findElementsByXPath(beforeXpathLinks);
		int cnt = allLinks.size();
		for (int i=1; i<=cnt; i++)
		{
			String linksText = driver.findElementByXPath(beforeXpathLinks + "[" +i+ "]" + afterXpathLinks).getText();
			System.out.println(linksText);
		}
	}

}
