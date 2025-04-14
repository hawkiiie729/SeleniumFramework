package myPersonalFramework.TestComponents;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import myPersonalFramework.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;// declaring globally

	public WebDriver initializeDriver() throws IOException {
		// properties class can read the global properties
		Properties prop = new Properties();// object creation for properties class
		// FileInputStream class converts your file into input stream object
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//myPersonalFramework//resources//GlobalData.properties");
		// we are getting the project path dynamically by system.getproperty ,so that it
		// runs on every system

		prop.load(fis);// this method will automatically parse the globaldata.properites file and
						// extract all key value pairs from it

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox code
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge code
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun=true) //so that when we run test in groups this always runs
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}
	
	@AfterMethod(alwaysRun=true) 
	public void tearDown() {
		driver.close();
	}

}
