package myPersonalFramework.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import myPersonalFramework.TestComponents.BaseTest;
import myPersonalFramework.pageobjects.CartPage;
import myPersonalFramework.pageobjects.CheckoutPage;
import myPersonalFramework.pageobjects.ConfirmationPage;
import myPersonalFramework.pageobjects.OrderPage;
import myPersonalFramework.pageobjects.ProductCatalouge;

public class submitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test
	public void submitOrderTest() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// use webdriver manager to invoke chromebrowser

		// encapsulating object creation in loginApplication method
		ProductCatalouge productCatalouge = landingPage.loginApplication("sudhanshu729@gmail.com", "S9935759084@m");

		List<WebElement> products = productCatalouge.getProductList();

		productCatalouge.addProductToCart(productName);

		// encapsulating object creation in goToCartPage method
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);

		// now we will check if match if true
		Assert.assertTrue(match);// asserations should not be in page object file ,it should only contains
									// actions
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrderTest" }) // this means first submitOrder Test will run then OrderHistoryTest
													// will run
	public void OrderHistoryTest() {

		ProductCatalouge productCatalouge = landingPage.loginApplication("sudhanshu729@gmail.com", "S9935759084@m");
		OrderPage orderPage = productCatalouge.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}
	// to verify ZARA COAT 3 is displaying in orders page

}
