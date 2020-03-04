package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import exec.TestExecutor;

public class WebDrivers extends TestExecutor{
	
	public WebDriver intializeBrowser() throws Exception {
		String strPath = System.getProperty("user.dir")+"//Drivers//chromedriver.exe";
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
		//syso
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
		
		//wb.findElement(By.name("username")).sendKeys("jeeten");
		//wb.findElement(By.xpath("//*[@value='Sign In']")).click();
	
	}
	
	public void citiTaskReport(WebDriver wb) throws Exception {
		wb.findElement(By.xpath("//a//div[text()='Analytics']")).click();
		Thread.sleep(500);
		wb.findElement(By.xpath("//a[text()='My Reports']")).click();
		Thread.sleep(500);
		wb.findElement(By.xpath("//*[@value='Run']")).click();
		Thread.sleep(2000);
		
	}
	
	public int reportVerifing(WebDriver wb, String columnName, String cellValue) throws Exception {
		String zID = wb.findElement(By.xpath("//div[contains(@id,'gridHolder_')]")).getAttribute("id").replace("gridHolder_", "");
		String row = "return $('#jqxgrid_"+zID+"').jqxGrid('getdatainformation').rowscount;";
		String col = "return $('#jqxgrid_"+zID+"').jqxGrid('columns').records.length;";
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rowCount  = js.executeScript(row).toString();
		String colCount  = js.executeScript(row).toString();
		int rows = Integer.getInteger(rowCount);
		int cols = Integer.getInteger(colCount);
		int count=0;
		String colDataField = "";
		for(int i=0; i<cols; i++) {
			col= "return $('#jqxgrid_"+zID+"').jqxGrid('columns').records["+i+"].text;";
			String colName = js.executeScript(col).toString();
			if(colName.equalsIgnoreCase(columnName)) {
				col= "return $('#jqxgrid_"+zID+"').jqxGrid('columns').records["+i+"].datafield;";
				colDataField = js.executeScript(col).toString();
			}
		}
		
		for(int i=0; i<rows; i++) {
			row = "return $('#jqxgrid_"+zID+"').jqxGrid('getcellvalue', "+i+", '"+colDataField+"');";
			String value = js.executeScript(row).toString();
			if(value.equalsIgnoreCase(cellValue)) {
				count=1;
				break;
			}
		}
		
		return count;
	}

}
