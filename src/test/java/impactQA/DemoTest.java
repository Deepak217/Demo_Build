package impactQA;

import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.File;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class DemoTest {

	WebDriver _driver;
	WebDriverWait wait;
	SoftAssert softAssertion = new SoftAssert();

	@BeforeMethod
	public void IntilizeBrowser() {
	
		String oS=System.getProperty("os.name");
		System.out.println("OS is "+oS);
		
		if(oS.contains("Linux"))
		{
			 final ChromeOptions chromeOptions = new ChromeOptions();
			    chromeOptions.setBinary("/usr/local/bin/chromedriver");
			    chromeOptions.addArguments("--headless");
			    chromeOptions.addArguments("--disable-gpu");
			    chromeOptions.addArguments("--no-sandbox-");
			    chromeOptions.addArguments("no-default-browser-check");
			 /*   chromeOptions.addArguments("");*/


			    final DesiredCapabilities dc = new DesiredCapabilities();
			    dc.setJavascriptEnabled(true);
			    dc.setCapability(
			        ChromeOptions.CAPABILITY, chromeOptions
			    );

			     _driver = new ChromeDriver(dc);
		}
		else
		{
			String path=  System.getProperty("user.dir")+File.separator+"BinaryFiles"+File.separator+"chromedriver.exe";
			System.out.println(path);
			System.setProperty("webdriver.chrome.driver", path);
			_driver = new ChromeDriver();
		}
		String url = "http://165.227.74.229/login";
		_driver.get(url);

	}

	@Test(priority = 0)

	public void verifyTitle()

	{
		try {
			wait = new WebDriverWait(_driver, 10);
			boolean title = wait.until(ExpectedConditions.titleIs("Sign In"));
			assertTrue(title, "Correct title displayed");

		} catch (Exception e) {
			fail();

		}

	}

	@Test(priority = 1)

	public void verifyLogin() {
		try {
			/* SoftAssert softAssertion = new SoftAssert(); */
			WebElement userEmail = _driver.findElement(By.id("email"));

			wait = new WebDriverWait(_driver, 10);
			wait.until(ExpectedConditions.visibilityOf(userEmail));
			userEmail.sendKeys("tbarry@ocalapd.org");
			WebElement userPassword = _driver.findElement(By.id("password"));
			wait.until(ExpectedConditions.visibilityOf(userPassword));

			userPassword.sendKeys("test");
			WebElement btn_Login = _driver.findElement(By.xpath("//form//div/div/button"));
			wait.until(ExpectedConditions.visibilityOf(btn_Login));
			btn_Login.click();
			WebElement logout = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='root']//span/img/../span")));
			String slogout = logout.getText();
			assertEquals("Log Out", slogout, "Login is successfull");
		} catch (Exception e) {
			fail("Unable to login: " + e.getMessage());

		}

	}

	@AfterMethod
	public void tearDown() {
		_driver.close();
		_driver.quit();
	}
}
