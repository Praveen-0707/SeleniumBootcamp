package leafground.page.webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingFrames {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on Image Frames
		WebElement imgFrames = driver.findElementByXPath("//h5[text()='Frame']/following-sibling::img");
		imgFrames.click();
		
//		Switches to first frame
		driver.switchTo().frame(0);
		driver.findElementById("Click").click();
		driver.switchTo().defaultContent();
		
//		Switches to second frame
		driver.switchTo().frame(1);
		driver.switchTo().frame("frame2");		// switches to nested frame
		driver.findElementById("Click1").click();
		driver.switchTo().defaultContent();
		
//		Switches to third frame
		driver.switchTo().frame(2);
		driver.switchTo().frame("frame2");		// switches to nested frame
		String frameText = driver.findElementByTagName("body").getText();
		System.out.println(frameText);
		driver.switchTo().defaultContent();
		
//		Prints total frame count
		List<WebElement> totalFrames = driver.findElementsByTagName("iframe");
		System.out.println("Total number of Frames: "+totalFrames.size());
	}

}
