package testNG;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGBaseClass {
		
	public RemoteWebDriver driver;
	public ChromeOptions chromeOptions;
	public FirefoxOptions firefoxOptions;
	public InternetExplorerOptions ieOptions;
	public EdgeOptions edgeOptions;
	public WebDriverWait wait;

	  @Parameters({"url","username","password"})
	  @BeforeMethod
	  public void login(String url, String userName, String pass) {
		driver.get(url);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username")))).sendKeys(userName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password")))).sendKeys(pass);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Login")))).click();
	  }

	  @AfterMethod
	  public void closeBrowser() throws InterruptedException {
		  Thread.sleep(2000);
		  driver.close();;
		  }


	  @BeforeClass
	  public void setupBrowser() {
		    
		  String browser = "Chrome";
		  
		  if (browser == "Chrome")
		  {
			  WebDriverManager.chromedriver().setup();
			  chromeOptions = new ChromeOptions();
			  chromeOptions.addArguments("--disable-notifications");
	//		  chromeOptions.addArguments("--headless");
	//		  chromeOptions.setHeadless(true);
			  driver = new ChromeDriver(chromeOptions);
		  }
	  
		  if (browser == "Firefox")
		  {
			  WebDriverManager.firefoxdriver().setup();
			  
			  FirefoxProfile profile = new FirefoxProfile();
			  profile.setPreference("app.update.auto", false);
			  profile.setPreference("app.update.enabled", false);
			  profile.setAcceptUntrustedCertificates(false);
			  profile.setPreference("browser.shell.checkDefaultBrowser", false);
			  
			  firefoxOptions = new FirefoxOptions();
	//		  firefoxOptions.addArguments("headless");
	//		  firefoxOptions.setHeadless(true);
			  firefoxOptions.setProfile(profile);
			  firefoxOptions.addPreference("dom.webnotifications.enabled", false);
			  driver = new FirefoxDriver(firefoxOptions);
		  }
		  
		  if (browser == "InternetExplorer")
		  {
			  WebDriverManager.iedriver().setup();
			  ieOptions = new InternetExplorerOptions();
			  ieOptions.disableNativeEvents();
			  ieOptions.requireWindowFocus();
			  ieOptions.ignoreZoomSettings();
			  ieOptions.introduceFlakinessByIgnoringSecurityDomains();
			  driver = new InternetExplorerDriver(ieOptions);
		  }
	  
		  if (browser == "Edge")
		  {
			  WebDriverManager.edgedriver().setup();
			  edgeOptions = new EdgeOptions();
			  edgeOptions.addArguments("disable-notifications");
			  driver = new EdgeDriver(edgeOptions);
		  }
		  
		  driver.manage().window().maximize();
		  wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
  }

}
