package myPersonalFramework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import myPersonalFramework.TestComponents.BaseTest;
import myPersonalFramework.pageobjects.CartPage;
import myPersonalFramework.pageobjects.CheckoutPage;
import myPersonalFramework.pageobjects.ConfirmationPage;
import myPersonalFramework.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// use webdriver manager to invoke chromebrowser
		String productName = "ZARA COAT 3";

		// encapsulating object creation in loginApplication method
		landingPage.loginApplication("sudhanshu@gmail.com", "S9935759084");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// use webdriver manager to invoke chromebrowser
		String productName = "ZARA COAT 3";

		// encapsulating object creation in loginApplication method
		ProductCatalouge productCatalouge = landingPage.loginApplication("sudhanshu.mh@gmail.com", "S9935759084@m");

		List<WebElement> products = productCatalouge.getProductList();

		productCatalouge.addProductToCart(productName);

		// encapsulating object creation in goToCartPage method
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");

		// now we will check if match if true
		Assert.assertFalse(match);// asserations should not be in page object file ,it should only contains
									// actions

	}

}
