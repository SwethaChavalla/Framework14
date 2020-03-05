package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import exec.TestExecutor;

public class WebDrivers extends TestExecutor{
	
	public WebDriver intializeBrowser() throws Exception {
		String strPath = System.getProperty("user.dir")+"//Webdrivers//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", strPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public String getUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public String getTitles() {
		String title = driver.getTitle();
		return title;
	}
	
	public void getCurrentPage( ) {
		String page = getUrl();
		
	}
	
	public void navigateToUrl(String url) {
		driver.navigate().to(url);
	}
	
	public void login(WebDriver wb) throws Exception {
		wb.findElement(By.name("username")).sendKeys("selenium_hd");
		wb.findElement(By.name("password")).sendKeys("fg");
		wb.findElement(By.xpath("//*[@value='Sign In']")).click();
		Thread.sleep(2000);
		//Assert.assertEquals("Fieldglass: System Admin", generic.getTitles());
	}
}
