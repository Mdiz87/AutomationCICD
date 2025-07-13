package seleniumFramework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class standAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//WebDriverManager.chromedriver().setup();
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("mmsahr27@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("#wwNL7C8@pv.jLi");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement selectedProduct = products.stream().filter(product-> 
			product.findElement(By.xpath("//h5/b")).getText().equals(productName)).findFirst().orElse(null);
		selectedProduct.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ngx-spinner"))));
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		driver.findElement(By.xpath("//button[contains(@routerlink, 'cart')]")).click();
		List <WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		//System.out.println(cartProducts);
		Boolean productFound = cartProducts.stream().anyMatch(cartProd-> cartProd.getText().equals(productName));
		Assert.assertTrue(productFound);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
				
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("yem");
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='ng-star-inserted']"))));
		List<WebElement> elements = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		for(WebElement element :elements) {
			if(element.getText().equalsIgnoreCase("yemen")) {
				element.click();
				break;
			}
		}
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
	}
}
