package landingPage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import landingPage.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Below are PageFactory Elements
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectedCountry;
	// button[contains(@class,'ta-item')][2]

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");// this can't be written in pageFactory because it is not a webElement

	// Below are actions
	public void selectCountry() {
		Actions a = new Actions(driver);
		// build() generates a composite action,containing all actions so far
		a.sendKeys(country, "india").build().perform();

		// now wait till the autosuggestive drop menu is displayed
		waitForElementToAppear(By.cssSelector(".ta-results"));

		selectedCountry.click();

		
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}

}
