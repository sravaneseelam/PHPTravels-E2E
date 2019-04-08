package phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PHPLandingPage extends BasePage {
	
	@FindBy(css = "ul.nav.navbar-nav.go-right > li:nth-child(1) > a")
	WebElement blogElm;
	
	@FindBy(css = "ul.nav.navbar-nav.go-right > li:nth-child(2) > a")
	WebElement offersElm;
	
	@FindBy(css = "div.collapse.navbar-collapse li#li_myaccount > a")
	WebElement myAccountElm;
	    
	@FindBy(css = "div.collapse.navbar-collapse li#li_myaccount > ul > li:nth-child(1) > a")
	WebElement loginOptionElm;
	
	@FindBy(css = "ul.nav.navbar-nav.navbar-right.hidden-sm.go-left > ul > ul > li")
	WebElement languageOptionsElm;
	
	@FindBy(css = "ul.nav.navbar-nav.navbar-right.hidden-sm.go-left > ul > ul > li > ul > li:nth-child(3)")
	WebElement frenchLangOptionElm;
	
	
	public PHPLandingPage(WebDriver driver) {
		super(driver);
	}
	
	public PHPLoginPage getLoginPage() {
		myAccountElm.click();
		loginOptionElm.click();
		
		return PageFactory.initElements(driver, PHPLoginPage.class);
	}
	
	public String getElementTitle(String genericTitle) {

		if(genericTitle.equalsIgnoreCase("BLOG")) {
			return blogElm.getText();
			
		} else if(genericTitle.equalsIgnoreCase("OFFERS")) {
			return offersElm.getText();
			
		} else if(genericTitle.equalsIgnoreCase("MY ACCOUNT")) {
			return myAccountElm.getText();
			
		} else if(genericTitle.equalsIgnoreCase("Login")) {
			return loginOptionElm.getText();
					
		} /*else if(genericTitle.equalsIgnoreCase("HOTELS")) {
			
		} else if(genericTitle.equalsIgnoreCase("FLIGHTS")) {
			
		} else if(genericTitle.equalsIgnoreCase("TOURS")) {
			
		} else if(genericTitle.equalsIgnoreCase("CARS")) {
			
		}*/
		return "";
	}
	
	public void selectLanguage(String chosenLanguage) {
		Actions actions = new Actions(driver);
		actions.moveToElement(languageOptionsElm);
		actions.build().perform();
		System.out.println("Did we moved to Language selection element.");
		if(chosenLanguage.equalsIgnoreCase("French")) {
			// waitUntilVisible(frenchLangOptionElm);
			actions.moveToElement(frenchLangOptionElm).build().perform();
			// frenchLangOptionElm.click();
			// Hack to move to french website, as Mouse Actions are not consistent
			driver.get("https://www.phptravels.net/fr");
		}
	}

}
