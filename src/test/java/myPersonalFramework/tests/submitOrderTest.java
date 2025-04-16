package myPersonalFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import myPersonalFramework.TestComponents.BaseTest;
import myPersonalFramework.pageobjects.CartPage;
import myPersonalFramework.pageobjects.CheckoutPage;
import myPersonalFramework.pageobjects.ConfirmationPage;
import myPersonalFramework.pageobjects.OrderPage;
import myPersonalFramework.pageobjects.ProductCatalouge;

public class submitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// use webdriver manager to invoke chromebrowser

		// encapsulating object creation in loginApplication method
		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalouge.getProductList();

		productCatalouge.addProductToCart(input.get("product"));

		// encapsulating object creation in goToCartPage method
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));

		// now we will check if match if true
		Assert.assertTrue(match);// asserations should not be in page object file ,it should only contains
									// actions
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" }) // this means first submitOrder Test will run then OrderHistoryTest
												// will run
	public void OrderHistoryTest() {
		// to verify ZARA COAT 3 is displaying in orders page
		ProductCatalouge productCatalouge = landingPage.loginApplication("sudhanshu.mh@gmail.com", "S9935759084@m");
		OrderPage orderPage = productCatalouge.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {


		// we can call this getJsonDataToMap method as it is defind in Parent class
		// (Base Test) ,therefore no separate object creation required
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//myPersonalFramework//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
	
	// hashMap for reference
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "sudhanshu729@gmail.com");
//	map.put("password", "S9935759084@m");
//	map.put("product", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "sudhanshu.mh@gmail.com");
//	map1.put("password", "S9935759084@m");
//	map1.put("product", "ADIDAS ORIGINAL");
//	

}
