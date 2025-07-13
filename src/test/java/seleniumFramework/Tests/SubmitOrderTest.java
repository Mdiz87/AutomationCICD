package seleniumFramework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumFramework.TestComponents.BaseTest;
import seleniumFramework.pageObjects.Cart;
import seleniumFramework.pageObjects.Checkout;
import seleniumFramework.pageObjects.ConfirmationPage;
import seleniumFramework.pageObjects.OrdersPage;
import seleniumFramework.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	/*String email = "mmsahr27@gmail.com";
	String password = "#wwNL7C8@pv.jLi";
	String productName = "ZARA COAT 3";*/


	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException { 
		String countryName = "Yemen";
		// Login and navigate to product catalogue page
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		WebElement selectedProduct = productCatalogue.getProductByName(input.get("product"));
		productCatalogue.addProductToCart(selectedProduct);
		// Proceed to cart page (Since cart can be accessed from multiple pages, the navigation to that page has been added to abstractComponents class that has all the reusable code)
		Cart cart = productCatalogue.proceedToCart();
		Boolean productFound = cart.assertProductAddedToCart(input.get("product"));
		Assert.assertTrue(productFound);
		// Proceed to checkout page
		Checkout checkout = cart.gotoCheckout();
		checkout.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkout.placeOrder();
		String title = confirmationPage.confirmMessage();
		Assert.assertEquals(title, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"}, groups = "purchase")
	public void orderHistoryTest(HashMap<String,String> input) {
		landingPage.loginApplication(input.get("email"), input.get("password"));
		OrdersPage ordersPage = new OrdersPage(driver);
		ordersPage.proceedToOrders();
		String actualProduct = ordersPage.checkProductInOrders(input.get("product"));
		Assert.assertEquals(actualProduct, input.get("product"));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data = getJsonDataToMap("\\src\\test\\java\\seleniumFramework\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
}
