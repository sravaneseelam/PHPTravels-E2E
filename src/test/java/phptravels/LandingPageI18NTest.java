package phptravels;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.BrowserConfigReader;
import utilities.LandingPageStringsENReader;
import utilities.LandingPageStringsFRReader;

public class LandingPageI18NTest extends BaseTest {

	protected LandingPageStringsENReader englishTitles;
	protected LandingPageStringsFRReader frenchTitles;
	
	@BeforeTest
	void setUp() {
		browserData = new BrowserConfigReader();
		englishTitles = new LandingPageStringsENReader();
		frenchTitles = new LandingPageStringsFRReader();
		
		initBrowser(browserData.getBrowserType());		
		driver.get(browserData.getBaseURL());		
		phpLandingPage = PageFactory.initElements(driver, PHPLandingPage.class);
	}
	
	@AfterTest
	void shutDown() {
		driver.close();
	}
	
	@Test(enabled=false, priority = 1, description = "Verify that all titles are in English.")
	void verifyEnglishTitles() {
		Assert.assertEquals(englishTitles.getPropertyValue("BLOG"), phpLandingPage.getElementTitle("BLOG"));
		Assert.assertEquals(englishTitles.getPropertyValue("OFFERS"), phpLandingPage.getElementTitle("OFFERS"));
	}

	@Test(priority = 2, description = "Verify that all titles are in French.")
	void verifyFrenchTitles() {
		phpLandingPage.selectLanguage("French");
		
		Assert.assertEquals(frenchTitles.getPropertyValue("BLOG"), phpLandingPage.getElementTitle("BLOG"));
		Assert.assertEquals(frenchTitles.getPropertyValue("OFFERS"), phpLandingPage.getElementTitle("OFFERS"));
	}
	
}
