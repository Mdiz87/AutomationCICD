package seleniumFramework.Tests;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import seleniumFramework.TestComponents.BaseTest;
import seleniumFramework.pageObjects.Cart;
import seleniumFramework.pageObjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest{

	@Test (groups = "ErrorHandling", retryAnalyzer = seleniumFramework.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException { 
		
		String email = "mmsahr27@gmail.com";
		String password = "#wwNL7C8@pv.jLi";
		// Login and navigate to product catalogue page
		landingPage.loginApplication(email, password + "error");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void ProductErrorValidation() throws IOException { 
		String productName = "ZARA COAT 3";
		String email = "mmsahr27@gmail.com";
		String password = "#wwNL7C8@pv.jLi";
		// Login and navigate to product catalogue page
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		productCatalogue.getProductList();
		WebElement selectedProduct = productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(selectedProduct);
		// Proceed to cart page (Since cart can be accessed from multiple pages, the navigation to that page has been added to abstractComponents class that has all the reusable code)
		Cart cart = productCatalogue.proceedToCart();
		Boolean productFound = cart.assertProductAddedToCart(productName+"33");
		Assert.assertFalse(productFound);
	}
}
