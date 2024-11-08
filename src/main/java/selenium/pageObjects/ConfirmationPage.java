package selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableComponents.ReusableComponent;

public class ConfirmationPage extends ReusableComponent{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	private WebElement confirmationMsg;
	

	public String getConfirmationMsg()
	{
		return confirmationMsg.getText();
	}
	
	
}
