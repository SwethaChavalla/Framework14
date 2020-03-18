package genericCommands;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import genericCommands.IndependentCommands;



public class TestExecutor {
	
	public WebDriver driver = null;
	public IndependentCommands generic = null;
	
	@BeforeTest
	public void initializeBrw() throws Exception {
		generic = new IndependentCommands();
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
	
}
