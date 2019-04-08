package phptravels;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.BrowserConfigReader;
import utilities.HotelTestDataReader;
import utilities.TestUserDataConfigReader;


public class PHPHotelSearchTest extends BaseTest {

	protected PHPLoginPage phpLoginPage = null;
	protected HotelTestDataReader testData4Hotels;

	@BeforeTest
	void setUp() {
		browserData = new BrowserConfigReader();
		testUserData = new TestUserDataConfigReader();
		testData4Hotels = new HotelTestDataReader();
		
		initBrowser(browserData.getBrowserType());		
		driver.get(browserData.getBaseURL());		
		phpLandingPage = PageFactory.initElements(driver, PHPLandingPage.class);
	}
	
	@AfterTest
	void shutDown() {
		driver.close();
	}

	@Test(priority = 1, description = "Verify that the logged in user can search for hotels.")
	void searchHotelsAsUser() {
		dashboardPage = phpLandingPage.getLoginPage().doLogIn(testUserData.getUserEmailID(), testUserData.getPassword());
		
		dashboardPage.clickOnHotelsSearchOpiton();
		
		//dashboardPage.searchForHotels("Paris", "TODAY", 2, 3, 2);
		dashboardPage.searchForHotels(testData4Hotels.getPropertyValue("CITY_NAME"),
										testData4Hotels.getPropertyValue("CHECKIN_DATE"),
										Integer.parseInt(testData4Hotels.getPropertyValue("NO_OF_DAYS_STAY")),
										Integer.parseInt(testData4Hotels.getPropertyValue("NO_OF_ADULTS")),
										Integer.parseInt(testData4Hotels.getPropertyValue("NO_OF_CHILDS")));

		Assert.assertEquals(dashboardPage.getSearchResultsCount() > 0, true);
		
	}

}
