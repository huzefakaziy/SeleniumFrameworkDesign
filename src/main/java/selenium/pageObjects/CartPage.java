package selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableComponents.ReusableComponent;

public class CartPage extends ReusableComponent{

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow .btn-primary")
	private WebElement checkoutButton;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean productMatch = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return productMatch;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
	
	
}
