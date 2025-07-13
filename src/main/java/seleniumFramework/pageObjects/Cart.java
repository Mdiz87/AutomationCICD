package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import seleniumFramework.AbstractComponents.AbstractComponents;

public class Cart extends AbstractComponents{

	
	WebDriver driver;
	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	 By productsInCart = By.xpath("//*[@class='cartSection']/h3");
	
	public Boolean assertProductAddedToCart(String productName) {
		List <WebElement> cartProducts = driver.findElements(productsInCart);
		Boolean productFound = cartProducts.stream().anyMatch(cartProd-> cartProd.getText().equals(productName));
		return productFound;
	}

	public Checkout gotoCheckout () {
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		return  new Checkout(driver);
	}
	
	
}
