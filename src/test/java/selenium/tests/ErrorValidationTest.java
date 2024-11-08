package selenium.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium.pageObjects.CartPage;
import selenium.pageObjects.ProductCataloguePage;
import selenium.testComponents.BaseTest;
import selenium.testComponents.Retry;

public class ErrorValidationTest extends BaseTest{

	@Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class, enabled = true)
	public void loginErrorValidationTest() throws IOException {		
		
		landingpage.loginApplication("dummyselenium1@gmail.com", "Pass12341");
		String errorMsg = landingpage.getErrorMsg();
		Assert.assertEquals("Incorrect email or password.", errorMsg);
	}
	
	@Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class, enabled = true)
	public void productErrorValidationTest() {
		String productName = "ZARA COAT 3";
		String invalidProductName = "ZARA COAT 33";
		
		ProductCataloguePage productCataloguePage = landingpage.loginApplication("sdfsd@jhkh.com", "Pass1234");
						
		productCataloguePage.addProductToCart(productName);
		CartPage cartPage = productCataloguePage.gotoCartPage();
					
		Boolean productMatch = cartPage.verifyProductDisplay(invalidProductName);
		Assert.assertFalse(productMatch);
	}
	
	@Test(invocationCount = 5)
	public void goTo()
	{
		
	}
	
	@Test(timeOut = 5000)
	public void timeOutTest()
	{
		
	}

}
