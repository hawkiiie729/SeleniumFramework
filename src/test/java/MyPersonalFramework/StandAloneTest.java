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

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// use webdriver manager to invoke chromebrowser
		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("sudhanshu729@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("S9935759084@m");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// find the product ZARA COAT 3
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		// card body div has 2 button element ,wehave to select the second button:add to
		// cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// now we have to wait and display the toast message that product is added to
		// the cart,we will use explicit wait for that

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// now we have to wait until the animation is invisible
		// ng-animating -> class name
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));// this
																											// method is
																											// faster
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();// generating css from partial text

		// findding by parent child traversal
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		// anyMatch will return true if value is matched or found else false
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		// now we will check if match if true
		Assert.assertTrue(match);
		// used class of parent tag and then used tag of the child element
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// now we move to the checkout page and we need to handle the autosuggestive
		// dropdown
		// will use actions class
		Actions a = new Actions(driver);
		// build() generates a composite action,containing all actions so far
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		// now wait till the autosuggestive drop menu is displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// travesring by index ,can do with both css and xpath
		// driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		// or
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		

	}

}
