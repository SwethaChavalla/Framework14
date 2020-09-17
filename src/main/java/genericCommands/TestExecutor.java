package genericCommands;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;


public class TestExecutor {
	
	public WebDriver driver = null;
	public IndependentCommands generic = null;
	
	@BeforeSuite()
	public void excelData() {
		new excelReader().getWBData();
	}
	
	/*@BeforeTest
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
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}*/

	@DataProvider(name = "dataProvider")
	public Object[][] getData() throws Exception {
		List<CsvData> records = excelReader.loadResults();
		int i=0;
		Object[][] data = new Object[records.size()][2];
		for (CsvData record : records) {
			if (record.getValues().get("Skip")!=null) {
				data[i][0] = record;
				i++;
			}	
			}
		//System.out.println(data);
		return data;
	}

	@Test(dataProvider = "dataProvider")
	public void test(ITestContext itc, CsvData data) {
		
		System.out.println("Test Method Data:"+data.getValues());
		
	}
}
