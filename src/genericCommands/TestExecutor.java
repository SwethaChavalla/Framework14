package exec;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import generic.WebDrivers;



public class TestExecutor {
	
	public WebDriver driver = null;
	public WebDrivers generic = null;
	
	@BeforeTest
	public void initializeBrw() throws Exception {
		generic = new WebDrivers();
		generic.intializeBrowser();
		driver=generic.getDriver();
		generic.navigateToUrl("https://sel5.fgvms.com/");
	}
	
	@Test
	public void loginToPage() throws Exception {
		generic.login(driver);
		System.out.println("Login Succesfully");
		//Assert.assertEquals("Fieldglass: System Admin", generic.getTitles());
		//  return generic.getTitles();
	}
	
	
	@Test(dependsOnMethods={"loginToPage"})
	public void main() throws Exception {
		try {
			System.out.println(" -----    I am in main method -------  ");
			//generic.citiTaskReport(driver);
			//generic.reportVerifing(driver,"","");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Listeners
	public void main2() throws Exception {
		try {
			System.out.println(" -----    I am in main method -------  ");
			//generic.citiTaskReport(driver);
			//generic.reportVerifing(driver,"","");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
