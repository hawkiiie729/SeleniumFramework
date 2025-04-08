package landingPage.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import landingPage.AbstractComponents.AbstractComponent;

//a page object should have only elements and action menthods ,no test data and assertions
public class ProductCatalouge extends AbstractComponent {

	WebDriver driver;// this driver variable is the local driver

	
	public ProductCatalouge(WebDriver driver) {
		// initialization
		super(driver);// we have to pass driver to parent in each page object class
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	

	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	
	// PageFactory->we can use page factory only if there is driver.findElement

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	// action methods->
	public List<WebElement> getProductList() {
		 
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
}
