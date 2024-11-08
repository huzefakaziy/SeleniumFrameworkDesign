package selenium.reusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.pageObjects.CartPage;
import selenium.pageObjects.OrdersPage;

public class ReusableComponent {

	WebDriver driver;
	WebDriverWait wait;	
	JavascriptExecutor js;
	
	public ReusableComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
	@FindBy(css="button[routerlink*=cart]")
	private WebElement cartHeader;
	
	@FindBy(css="button[routerlink*=myorders]")
	private WebElement orderHeader;
	
	public CartPage gotoCartPage()
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrdersPage gotoOrdersPage()
	{
		orderHeader.click();
		return new OrdersPage(driver);
	}
	
	public void executeJavaScript(WebElement ele)
	{
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void waitForByLocatorToAppear(By findBy)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForByLocatorToDisappear(By findBy)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForWebElementToDisappear(WebElement element)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
}
