package phptravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PHPLoginPage extends BasePage {
	
	@FindBy(css = "[name='username']")
	WebElement emailIdElm;
	    
	@FindBy(css = "[name='password']")
	WebElement passwordElm;
	
	@FindBy(id = "remember-me")
	WebElement rememberMeElm;

	@FindBy(css = "form#loginfrm button")
	WebElement logInButtonElm;
	
	@FindBy(css = "div.resultlogin > div")
	WebElement wrongCredentialsErrorMessageElm;
	
	@FindBy(css = "ul.nav.navbar-nav.go-right > li:nth-child(1)")
	WebElement hotelSearchOptionElm;
	
	public PHPLoginPage(WebDriver driver) {
		super(driver);
	}
	
	public UserDashboardPage doLogIn(String emailId, String password) {
		
	     emailIdElm.sendKeys(emailId);
	     passwordElm.sendKeys(password);
	     logInButtonElm.click();
	     waitUntilVisible(hotelSearchOptionElm);
	     
	     return PageFactory.initElements(driver, UserDashboardPage.class);
	 }
	
   public String getErrorMessage() {
	     return wrongCredentialsErrorMessageElm.getText();
	}
}
