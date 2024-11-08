package selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableComponents.ReusableComponent;

public class LandingPage extends ReusableComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#userEmail")
	private WebElement userEmail;
	
	@FindBy(css="#userPassword")
	private WebElement userPassword;
	
	@FindBy(css="#login")
	private WebElement loginSubmit;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorMsg;
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	
	
	public ProductCataloguePage loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginSubmit.click();
		return new ProductCataloguePage(driver);
	}
	
	public String getErrorMsg()
	{
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
}
