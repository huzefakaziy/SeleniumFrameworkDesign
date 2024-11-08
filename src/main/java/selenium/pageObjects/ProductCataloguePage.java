package selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableComponents.ReusableComponent;

public class ProductCataloguePage extends ReusableComponent{

	WebDriver driver;
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	private List<WebElement> productList;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	private By productsBy = By.cssSelector(".mb-3");
	private By addToCart = By.cssSelector("div.card-body button:nth-of-type(2)");
	private By toastMsg = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductsList()
	{
		waitForByLocatorToAppear(productsBy);
		return productList;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement product = getProductsList().stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForByLocatorToAppear(toastMsg);
		waitForWebElementToDisappear(spinner);
	}
}
