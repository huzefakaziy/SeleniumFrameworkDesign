package selenium.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import selenium.pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	public static Properties properties;
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException
	{
		driver = initializeDriver(driver);
		landingpage = new LandingPage(driver);		
		landingpage.goTo(properties.getProperty("url"));
		return landingpage;
	}
	
	public WebDriver initializeDriver(WebDriver driver) throws IOException
	{
		FileInputStream fis = new FileInputStream("src\\main\\java\\selenium\\resources\\GlobalData.properties");
		properties = new Properties();
		properties.load(fis);
		String browser =  System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");
				
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			if(properties.getProperty("mode").equalsIgnoreCase("headless"))
			{
				options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);		
		}
		else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-notifications");
			driver = new EdgeDriver(options);
		}
		else
		{
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--disable-notifications");
			driver = new FirefoxDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitlyWaitInSec"))));		
		driver.manage().window().setSize(new Dimension(1440, 900));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	
	public List<HashMap<Object, Object>> getJSONData(String fileName) throws IOException
	{
		String filePath = "src\\test\\java\\selenium\\testData\\" + fileName;
		//Step1: Convert JSON file to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		 
		//Step2: Convert String to List of Hashmaps using Jackson Databind ObjectMapper Class		
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<Object, Object>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<Object, Object>>>() {
		});
		
		//Return List of HashMaps converted from JSON file
		return data;
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		String screenshotFilePath = System.getProperty("user.dir") + "\\ScreenShots\\" + testcaseName + ".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(screenshotFilePath); 
		FileUtils.copyFile(srcFile, destFile);
		//System.out.println("In getScreehot: " + screenshotFilePath);
		return screenshotFilePath;
	}
	

	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}

}
