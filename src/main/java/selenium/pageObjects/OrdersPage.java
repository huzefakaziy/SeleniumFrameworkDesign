package selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableComponents.ReusableComponent;

public class OrdersPage extends ReusableComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody tr td:nth-child(3)")
	private List<WebElement> orderProducts;
	
	
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean productMatch = orderProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return productMatch;
	}
	
	
	
	
	
}
