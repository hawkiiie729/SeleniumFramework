package MyPersonalFramework;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import landingPage.pageobjects.CartPage;
import landingPage.pageobjects.CheckoutPage;
import landingPage.pageobjects.ConfirmationPage;
import landingPage.pageobjects.LandingPage;
import landingPage.pageobjects.ProductCatalouge;

public class submitOrderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// use webdriver manager to invoke chromebrowser
		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();

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

		driver.close();

	}

}
