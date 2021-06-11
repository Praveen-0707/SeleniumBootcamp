package leafground.page.webelements;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingRadioButtonsAndCheckbox {

public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on RadioButton Image
		WebElement imgRadioButton = driver.findElementByXPath("//h5[text()='Radio Button']/following-sibling::img");
		imgRadioButton.click();
		
		WebElement radioBtnClk = driver.findElementById("yes");
		boolean isEnabled = radioBtnClk.isEnabled();
		if (isEnabled)
		{
			radioBtnClk.click();
		}
		
		WebElement radioBtnSelected = driver.findElementByXPath("//label[@for='Checked']/input");
		boolean isSelected = radioBtnSelected.isSelected();
		if (isSelected)
		{
			System.out.println("Value Selected");
		}
		
//		String xPath = "//label[contains(text(),'Select your age group')]/parent::div/text()[9]/preceding-sibling::input";
//		int selectAge = 18;
//		
//		List<WebElement> options = driver.findElementsByXPath("//label[contains(text(),'Select your age group')]/parent::div/text()");
//		for (int i =1; i<options.size(); i++)
//		{
//			WebElement iOptions = driver.findElementByXPath("//label[contains(text(),'Select your age group')]/parent::div/text()["+i+"]");
//			String textValue = iOptions.getText();
//			System.out.println(textValue);
//			if ((selectAge <=20) && textValue.contains("1 - 20 years"))
//			{
//				driver.findElementByXPath(xPath + "[last()]").click();
//			}
//			else if ((selectAge >=20) && (selectAge <=40) && textValue.contains("21 - 40 years"))
//			{
//				driver.findElementByXPath(xPath + "[last()-1]").click();
//			}
//			else if ((selectAge >40) && textValue.contains("Above 40 years"))
//			{
//				driver.findElementByXPath(xPath + "[last()-2]").click();
//			}
//		}
		
		driver.navigate().back();
		Thread.sleep(2000);
		
//		Clicks on CheckBox Image
		WebElement imgCheckbox = driver.findElementByXPath("//h5[text()='Checkbox']/following-sibling::img");
		imgCheckbox.click();
		
//		Selecting languages
		List<WebElement> allOptions = driver.findElementsByXPath("//label[text()='Select the languages that you know?']/following-sibling::div");
		for (int i =1; i<=allOptions.size(); i++)
		{
			WebElement options = driver.findElementByXPath("//label[text()='Select the languages that you know?']/following-sibling::div["+i+"]");
			String course = options.getText();
			System.out.println(course);
			if (course.contains("Java") || course.contains("SQL") || course.contains("C++"))
			{
				driver.findElementByXPath("//label[text()='Select the languages that you know?']/following-sibling::div["+i+"]/input").click();
			}
		}
		
		
		boolean isChkBoxSelected = driver.findElementByXPath("//div[text()='Selenium']/input").isSelected();
		System.out.println(isChkBoxSelected);
		
	}

}
