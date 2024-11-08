package selenium.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pageObjects.CartPage;
import selenium.pageObjects.CheckoutPage;
import selenium.pageObjects.ConfirmationPage;
import selenium.pageObjects.ProductCataloguePage;
import selenium.testComponents.BaseTest;

public class StepDefImplementation extends BaseTest{
	
	private ProductCataloguePage productCataloguePage;
	private ConfirmationPage confirmationPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	
	private String countryName = "India";
	

	@Given("I landed on Ecommerce Page")
	public void landed_on_Ecommerce_Page() throws IOException {
		launchApp();		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String password) {
		productCataloguePage = landingpage.loginApplication(userName, password);
	}
	
	 @When("^I add product (.+) to Cart$")
	 public void add_product_to_Cart(String productName) {
		 productCataloguePage.addProductToCart(productName);
		
	 }
	 
	 @And("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName) {
		cartPage = productCataloguePage.gotoCartPage();
		Boolean productMatch = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(productMatch);		
		checkoutPage = cartPage.goToCheckout();		
		checkoutPage.selectCountry(countryName);
		confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on Confirmation Page")
	 public void message_is_displayed_on_Confirmation_Page(String msg) {
		String confirmationMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase(msg));
		driver.quit();
	 }
	 
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String msg) {		
		Assert.assertEquals(msg, landingpage.getErrorMsg());
		driver.quit();
	 }
	
}
