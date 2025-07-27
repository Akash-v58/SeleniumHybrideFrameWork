package genericlibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static DataUtility dataUtility = new DataUtility();
	public WebDriver driver;

	@Parameters("Browser")
	@BeforeClass(groups = { "smoke", "Regression" })
	public void launchBrowser(@Optional("Chrome") String browser) {
		if (browser.equals("Chrome")) {
			ChromeOptions options = new ChromeOptions();

	        // Headless mode required for CI/CD like GitHub Actions
	        options.addArguments("--headless=new"); // Use "--headless=new" for modern Chrome
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--window-size=1920,1080"); // optional: set screen size

	        driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} else if (browser.equals("FireFox")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-headless");
            firefoxOptions.addArguments("--width=1920");
            firefoxOptions.addArguments("--height=1080");
            driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
		} else if (browser.equals("Edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("headless");
            edgeOptions.addArguments("disable-gpu"); // optional, useful for older versions
            edgeOptions.addArguments("window-size=1920,1080");
            driver = new EdgeDriver(edgeOptions);
			driver.manage().window().maximize();
		} else {
			ChromeOptions options = new ChromeOptions();

	        // Headless mode required for CI/CD like GitHub Actions
	        options.addArguments("--headless=new"); // Use "--headless=new" for modern Chrome
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--window-size=1920,1080"); // optional: set screen size

	        driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups = { "smoke", "Regression" })
	public void closeBrowser() {
		driver.quit();
	}

	@BeforeMethod(groups = { "smoke", "Regression" })
	public void login() throws IOException {
		driver.get(dataUtility.getDataFromProperties("url"));
		driver.findElement(By.xpath("//a[ @class = 'ico-login']")).click();
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Login", "login not displayed");
		driver.findElement(By.id("Email")).sendKeys(dataUtility.getDataFromProperties("username"));
		driver.findElement(By.id("Password")).sendKeys(dataUtility.getDataFromProperties("password"));
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop", "shop page not displayed");
	}

	@AfterMethod(groups = { "smoke", "Regression" })
	public void logout() {
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
	}
}
