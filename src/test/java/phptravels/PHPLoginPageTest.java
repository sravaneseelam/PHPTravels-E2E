package phptravels;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.BrowserConfigReader;
import utilities.TestUserDataConfigReader;


public class PHPLoginPageTest extends BaseTest {

	protected PHPLoginPage phpLoginPage = null;

	@BeforeTest
	void setUp() {
		testUserData = new TestUserDataConfigReader();
		browserData = new BrowserConfigReader();
		
		initBrowser(browserData.getBrowserType());		
		driver.get(browserData.getBaseURL());
		
		phpLandingPage = PageFactory.initElements(driver, PHPLandingPage.class);
	}
	
	@AfterTest
	void shutDown() {
		driver.close();
	}
	
	@BeforeMethod
	void refreshPage() {
		driver.navigate().refresh();
	}
	
	@Test(priority = 1, description = "Verify that the correct error message shown when tries to login with wrong credentials.")
	void doLoginWithWrongCredentials() {
		
		phpLoginPage = phpLandingPage.getLoginPage();
		phpLoginPage.doLogIn(testUserData.getUserEmailID(), testUserData.getIncorrectPassword());
		Assert.assertEquals("Invalid Email or Password", phpLoginPage.getErrorMessage());
	}

	@Test(priority = 2, description = "Verify that the user can login with correct credentials.")
	void doLoginWithCorrectCredentials() {
		dashboardPage = phpLoginPage.doLogIn(testUserData.getUserEmailID(), testUserData.getPassword());
		Assert.assertEquals("Hi, Johny Smith", dashboardPage.getUserName());
		
	}

}
