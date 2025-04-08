package landingPage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import landingPage.AbstractComponents.AbstractComponent;

//a page object should have only elements and action menthods ,no test data
public class LandingPage extends AbstractComponent {

	WebDriver driver;// this driver variable is the local driver

	// contructor is the same name as class name,and it will be the first method to
	// execute
	// contructor is used to write the initialization code
	public LandingPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;// we are storing the driver value to the local driver before the scope is ended
		PageFactory.initElements(driver, this);// it initialize all the elements(ex. userEmail)

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory->

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;

	// action methods->
	public ProductCatalouge loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		return productCatalouge;//because we are sure that after login we are going to productCataloguge page

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}

}
