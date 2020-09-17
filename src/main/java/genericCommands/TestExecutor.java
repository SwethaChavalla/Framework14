package genericCommands;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;


public class TestExecutor {
	
	public static LinkedHashMap<String, TestProperties> testProps = new LinkedHashMap<String, TestProperties>();
	public WebDriver driver = null;
	public IndependentCommands generic = null;
	
	static class Property {
		synchronized public TestProperties getProperties(String testName) {
			if (!testProps.containsKey(testName)) {
				testProps.put(testName, new TestProperties());
			}
			return testProps.get(testName);
		}
	}
	
	private static Property prop;
	
	private static Property property() {
		if(prop == null) {
			return new Property();
		}else{
			return prop;
		}
	}
	
	@BeforeTest
	@Parameters({"browserName", "serverUrl", "path", "properties", "datafile"})
	public void beforeTestLevel(ITestContext itc,
			String browserName, String serverUrl, String path, String properties, String datafile) throws Exception {
		String suiteName = itc.getSuite().getName();
		String testName = itc.getName();
		TestProperties testProp = property().getProperties(suiteName+testName);
		System.out.println(testProp);
		testProp.browserName=browserName;
		testProp.url=serverUrl;
		testProp.properties = new Properties();
		FileInputStream fis = new FileInputStream(properties);
		testProp.properties.load(fis);
	}
	
	
	@Test
	public void loginToPage() throws Exception {
		generic.login(driver);
		System.out.println("Login Succesfully");
	}
	
	
	
}
