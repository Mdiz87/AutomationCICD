package seleniumFramework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement passwordElement;
	@FindBy(id="login")
	WebElement submitButton;
	By errorAlert = By.xpath("//div[@role= 'alert']");
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submitButton.click();
		return new ProductCatalogue(driver);
	}
	
	public void openPage(String url) {
		driver.get(url);
	}

	public String getErrorMessage() {
		waitForElementsToAppear(errorAlert);
		String errorMessage = driver.findElement(errorAlert).getText();
		return errorMessage;
	}
	
}
