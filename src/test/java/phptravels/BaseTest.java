package phptravels;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.BrowserConfigReader;
import utilities.TestUserDataConfigReader;

public class BaseTest {
	
	protected static WebDriver driver = null;
	protected TestUserDataConfigReader testUserData;
	protected BrowserConfigReader browserData;
	protected PHPLandingPage phpLandingPage;
	protected UserDashboardPage dashboardPage;
	
	public static WebDriver initBrowser(String browserType) {

		if(browserType.equalsIgnoreCase("chrome")) {
			
			ChromeDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		}else if(browserType.equalsIgnoreCase("firefox")) { 
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		
		
		}else if(browserType.equalsIgnoreCase("ie")) {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		
		}else {
			
			System.out.println("Can not Find given Driver: Running Test with Chrome Driver");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		return driver;
	}

}
