package TrainingSelenium;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {		
		
		String productName = "ZARA COAT 3";
		String countryName = "India";
		
		WebDriver driver = new ChromeDriver();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("dummyselenium@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Pass1234");
		driver.findElement(By.cssSelector("#login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("div.card-body button:nth-of-type(2)")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean productMatch = CartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(productMatch);
				
		driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), countryName).build().perform();
		
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		//js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		
		
		/*
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(country);
		
		List<WebElement> countryList = driver.findElements(By.cssSelector(".form-group section button"));
		WebElement selectCountry = countryList.stream().filter(s -> s.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		selectCountry.click();
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.action__submit")));
		driver.findElement(By.cssSelector("a.action__submit")).click();
		String OrderID = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
		System.out.println(OrderID);
		*/
		
		
	}

}
