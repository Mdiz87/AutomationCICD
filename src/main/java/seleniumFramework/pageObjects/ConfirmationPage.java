package seleniumFramework.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumFramework.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	 
	By purchaseTitle = By.xpath("//h1[@class = 'hero-primary']");
	
	
	public String confirmMessage() {
		return driver.findElement(purchaseTitle).getText();	
	}
	
}
