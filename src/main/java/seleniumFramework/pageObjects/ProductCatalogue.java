package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	
	By productsList = By.cssSelector(".mb-3");
	By productNames = By.xpath("//h5/b");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	//By addToCart = By.xpath("//div[@class='card-body']/button[text()=' Add To Cart']");
	By toastMessage = By.id("toast-container");
	By spinner = By.xpath("//ngx-spinner");

	public List<WebElement> getProductList(){
		waitForElementsToAppear(productsList);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement selectedProduct = getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return selectedProduct;
	}
	
	public void addProductToCart(WebElement element) {
		element.findElement(addToCart).click();
		waitForElementsToAppear(toastMessage);
		waitForElementsToDisappear(spinner);
	}
	
	
}
