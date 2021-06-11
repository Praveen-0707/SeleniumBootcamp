package leafground.page.webelements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingLinks {	
	
	public static void main(String[] args)
	{
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		String URL = "http://leafground.com";
		driver.get(URL);
		
//		Clicks on Image Links
		WebElement imgLink = driver.findElementByXPath("//h5[text()='HyperLink']/following-sibling::img");
		imgLink.click();
		
//		to find where the link is navigating to
		WebElement getNavPoint = driver.findElementByXPath("//a[contains(text(),'Find where')]");
		String link = getNavPoint.getAttribute("href");
		System.out.println(link);	

	  // List of all links and images
	  List<WebElement> linkImgList = driver.findElements(By.tagName("a"));
	  linkImgList.addAll(driver.findElements(By.tagName("img")));

	  // Total Links and Images Before
	  int total = linkImgList.size();
	  System.out.println("Total Links and Images are: " + total);
	  
//	  to store all active links
	  List<WebElement> activeLinkImage = new ArrayList<WebElement>();
	  
	  int validLink = 0;
	  int invalidLink = 0;
		 
	  for (int i = 0; i < linkImgList.size(); i++)
	  {
		  if (linkImgList.get(i).getAttribute("href") != null)
		  {
			  activeLinkImage.add(linkImgList.get(i));
		  }
	  }
	  
	  // Total Active Links and Images
	  int total1 = activeLinkImage.size();
	  System.out.println("Total Active Links and Images: " + total1);
	 
//	  Checking for broken Links and Images
	  System.out.println("******Checking for broken Links and Images******");
	  for(int j=0;j<activeLinkImage.size();j++)
	  {
		  try
		  {
			  String url = activeLinkImage.get(j).getAttribute("href");
			  HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();
			  con.connect();
			  int responseCode = con.getResponseCode();
			  String responseMessage = con.getResponseMessage();
			  con.disconnect();
			  
			  if (responseCode > 400)
			  {
				  invalidLink = invalidLink + 1;
				  System.out.println("Broken Link, URL -->: " + url + "--->" + responseCode + ", response - " + responseMessage);
			  }
			  else
			  {
				  validLink = validLink + 1;
				  System.out.println("Valid Link with code and response: " + responseCode + "--->" + responseMessage);
			  }
		  }
		  catch(MalformedURLException e)
		  {
			  e.printStackTrace();
		  }
		  catch(IOException e)
		  {
			  e.printStackTrace();
		  }
	  }
	  
	  System.out.println("Total Valid Links: " + validLink);
	  System.out.println("Total Invalid Links: " + invalidLink);
	 

	}
}