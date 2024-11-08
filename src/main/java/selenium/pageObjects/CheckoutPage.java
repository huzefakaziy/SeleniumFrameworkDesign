package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableComponents.ReusableComponent;

public class CheckoutPage extends ReusableComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow .btn-primary")
	private WebElement button;
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	private WebElement selectCountry;
	
	@FindBy(css="a.btnn.action__submit.ng-star-inserted")
	private WebElement submitButton;
	
		
	private By results = By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForByLocatorToAppear(results);		
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{			
		executeJavaScript(submitButton);
		return new ConfirmationPage(driver);
	}
	
}
