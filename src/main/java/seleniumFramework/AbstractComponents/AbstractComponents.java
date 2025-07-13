package seleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFramework.pageObjects.Cart;
import seleniumFramework.pageObjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	By cartButton = By.xpath("//button[contains(@routerlink, 'cart')]");
	By ordersButton = By.xpath("//button[@routerlink = '/dashboard/myorders']");
	
	public Cart proceedToCart() {
		driver.findElement(cartButton).click();
		return new Cart(driver);
	}
	
	public OrdersPage proceedToOrders() {
		driver.findElement(ordersButton).click();
		return new OrdersPage(driver);
	}
	
	public void waitForElementsToAppear(By findBy) {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementsToDisappear(By findBy) {
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
}
