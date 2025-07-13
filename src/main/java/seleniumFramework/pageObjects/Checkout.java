package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import seleniumFramework.AbstractComponents.AbstractComponents;

public class Checkout extends AbstractComponents {
	
	WebDriver driver;

	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By countryDropDown = By.xpath("//input[@placeholder='Select Country']");
	By dropDownOptions = By.xpath("//span[@class='ng-star-inserted']");
	By placeOrderButton = By.xpath("//a[text()='Place Order ']");
	
	public void selectCountry(String countryName) {
		driver.findElement(countryDropDown).sendKeys(countryName);
		waitForElementsToAppear(dropDownOptions);
		List<WebElement> elements = driver.findElements(dropDownOptions);
		for(WebElement element :elements) {
			if(element.getText().equalsIgnoreCase(countryName)) {
				element.click();
				break;
			}
		}
	}
	
	public ConfirmationPage placeOrder() {
		driver.findElement(placeOrderButton).click();
		return new ConfirmationPage(driver);
	}
}
