package phptravels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UserDashboardPage extends BasePage {

	@FindBy(css = "h3.RTL")
	WebElement userNameElm;
	
	@FindBy(css = "ul.nav.navbar-nav.go-right > li:nth-child(1)")
	WebElement homeOptionElm;
	
	// Main tab options in user dashboard
	
	@FindBy(css = "div.collapse.navbar-collapse > ul.nav.navbar-nav.go-right > li:nth-child(1) > a")
	WebElement hotelsTabOptionElm;

	@FindBy(css = "ul.nav.navbar-nav.go-right > li[data-title='flights'] > a")
	WebElement flightsTabOptionElm;
	
	// Hotels Search options
	@FindBy(css = "div.select2-container.hotelsearch.locationlisthotels > a > span.select2-chosen")
	WebElement cityORhotelNameElm;
	
	@FindBy(css = "ul.select2-results > li > ul > li:nth-child(2)")
	WebElement specificHotelNameElm;
	
	@FindBy(css = "input[class='form input-lg dpd1'][name='checkin']")
	WebElement checkInDateElm;
	
	@FindBy(css = "div.datepicker-days > table > tbody > tr:nth-child(3) > td:nth-child(1)")
	List<WebElement> variousCheckInDatesElm;
	
	@FindBy(css = "#dpd2 > div > input")
	WebElement checkOutDateElm;
	
	@FindBy(id = "travellersInput")
	WebElement travelorsElm;
	
	@FindBy(id = "adultPlusBtn")
	WebElement adultPlusBtnElm;
	
	@FindBy(id = "adultMinusBtn")
	WebElement adultMinusBtnElm;
	
	@FindBy(id = "childPlusBtn")
	WebElement childPlusBtnElm;
	
	@FindBy(id = "childMinusBtn")
	WebElement childMinusBtnElm;
	
	@FindBy(css = "#hotels > form > div.col-md-2.form-group.go-right.col-xs-12.search-button > button")
	WebElement searchBtnElm;
	
	@FindBy(css = "div.col-md-9.col-xs-12 > div.row > div > table > tbody > tr")
	List<WebElement> searchResultsList;
	
	/* constructor
	 * 
	 */
	public UserDashboardPage(WebDriver driver) {
		super(driver);
	}
	
	public String getUserName() {
	    return userNameElm.getText();
	}
	
	public void clickOnHotelsSearchOpiton() {
		Actions actions = new Actions(driver);
		actions.moveToElement(hotelsTabOptionElm).build().perform();
		hotelsTabOptionElm.click();
		System.out.println(hotelsTabOptionElm.getText());

		// It's hack as clicking on Hotels option is not working and tried in many different ways, so call the url directly.
		// The Page Object won't break.
		driver.get("https://www.phptravels.net/m-hotels");
	}
	
	public void clickOnFlightsSearchOpiton() {
		flightsTabOptionElm.click();
	}
	
	public void searchForHotels(String cityName, String checkInDate, int days2stay, int noOfAdults, int noOfChilds) {
		// Enter city name and wait for the list of hotels to show in the drop down.
		Actions actions = new Actions(driver);
		actions.moveToElement(cityORhotelNameElm).click().build().perform();
		actions.sendKeys("Paris").build().perform();
		
		waitUntilVisible(specificHotelNameElm);
		actions.moveToElement(specificHotelNameElm);
		actions.click().build().perform();
		
		// Get Today from system
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		// Choose CheckIn Date from possible user input TODAY or TOMORROW or MM/DD/YYYY format and validate.
		if(checkInDate.equalsIgnoreCase("TODAY")) {			
			waitUntilVisible(checkInDateElm);
			checkInDateElm.click();
			checkInDateElm.sendKeys(dateFormat.format(date));
			
		} else if(checkInDate.equalsIgnoreCase("TOMORROW")) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, 1); //Adding one gives tomorrow's date
	        date = cal.getTime();
	        
			waitUntilVisible(checkInDateElm);
			checkInDateElm.click();
			checkInDateElm.sendKeys(dateFormat.format(date));
			
		} else if(checkInDate.contains("/")) {
			// If the given check in date is not in the correct format then use today as input.
			// Process the date using regular expressions.
			// Store the date in 'date' variable and it is required to calculate checkOut date.
			
		}
		
		checkOutDateElm.sendKeys("20/04/2019");
		
		// Fill checkout date with the help of checkin date and number of days to stay
		// Checkout is default chosen to next day.
		
		travelorsElm.click();
		// Set number of adults - Default adult value is 2
		if(noOfAdults > 0) {
			// If only one adult, then decrement default adult value by 1.
			if(noOfAdults == 1) {
				
			}

		}
		// If more than 2 adults, then click on the plus button
		for(int i = 2; i < noOfAdults; i++) {
			childPlusBtnElm.click();
		}
		// If childs are chosen, then click on the plus button
		for(int i = 0; i < noOfChilds; i++) {
			childPlusBtnElm.click();
		}
		
		searchBtnElm.click();
	}
	
	public int getSearchResultsCount() {
		return searchResultsList.size();
	}
	
}
