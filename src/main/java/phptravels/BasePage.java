package phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver = null;
	protected WebDriverWait wait = null;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}

	public void waitUntilVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForVisibility(String cssSelector) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(cssSelector))));
	}
	
	public void waitForClicking(String cssSelector) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(cssSelector))));
	}
	
	public void waitForVisibilityXpath(String selector) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(selector))));
	}
}