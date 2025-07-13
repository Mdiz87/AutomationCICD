package seleniumFramework.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumFramework.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{

	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public String checkProductInOrders(String productName) {
		
		By productNameLocator = By.xpath("//tr[@class = 'ng-star-inserted']/td[text() = '"+productName+"']");
		waitForElementsToAppear(productNameLocator);
		return driver.findElement(productNameLocator).getText();
	}
	
}
