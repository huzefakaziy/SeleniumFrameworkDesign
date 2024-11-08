package selenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.pageObjects.CartPage;
import selenium.pageObjects.CheckoutPage;
import selenium.pageObjects.ConfirmationPage;
import selenium.pageObjects.OrdersPage;
import selenium.pageObjects.ProductCataloguePage;
import selenium.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	String productName;
	@Test(dataProvider = "getData", groups = {"purchaseOrder", "smoke"}, enabled = true)	
	public void submitOrderTest(HashMap<Object, Object> testData) throws IOException {		
		String countryName = "India";
		String loginID = (String)testData.get("loginID");
		String pwd = (String)testData.get("pwd");
		this.productName = (String)testData.get("productName");
		
		//Login to Application 
		ProductCataloguePage productCataloguePage = landingpage.loginApplication(loginID, pwd);
		// Add products to Cart
		productCataloguePage.addProductToCart(productName);
		//Go to Cart Page
		CartPage cartPage = productCataloguePage.gotoCartPage();
		// Verify the Product is present on the Cart page
		Boolean productMatch = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(productMatch);
		//Checkout
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		//Select Country on checkout page
		checkoutPage.selectCountry(countryName);
		//Submit the Order
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		//Get the confirmation msg from Confirmation page
		String confirmationMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
	}
	
	@Test(dependsOnMethods = {"submitOrderTest"}, enabled = true)
	public void orderHistoryTest()
	{		
		ProductCataloguePage productCataloguePage = landingpage.loginApplication("dummyselenium@gmail.com", "Pass1234");
		OrdersPage ordersPage = productCataloguePage.gotoOrdersPage();
		Boolean orderDisplay = ordersPage.verifyOrderDisplay(productName);
		Assert.assertTrue(orderDisplay);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{	
		List<HashMap<Object, Object>> dataList = getJSONData("PurchaseOrder.json");
		Object[][] data = new Object[dataList.size()][1];
		
		for(int i=0; i<dataList.size(); i++)
		{
			data[i][0] = dataList.get(i);
		}
		
		return data;		
	}
	
	/*
	@DataProvider
	public Object[][] getData() throws IOException
	{	
		Object[][] data = new Object[3][3];
		data[0][0] = "sdfsd@jhkh.com";
		data[0][1] = "Pass1234";
		data[0][2] = "ZARA COAT 3";
		data[1][0] = "jhkh@sdfsd.com";
		data[1][1] = "Pass1234";
		data[1][2] = "ADIDAS ORIGINAL";
		data[2][0] = "dummyselenium@gmail.com";
		data[2][1] = "Pass1234";
		data[2][2] = "IPHONE 13 PRO";				
		OR
		Object[][] data = {{"sdfsd@jhkh.com","Pass1234","ZARA COAT 3"},{"jhkh@sdfsd.com","Pass1234","ADIDAS ORIGINAL"},{"dummyselenium@gmail.com","Pass1234","IPHONE 13 PRO"}};
		return data;
		OR
		return new Object[][] {{"sdfsd@jhkh.com","Pass1234","ZARA COAT 3"},{"jhkh@sdfsd.com","Pass1234","ADIDAS ORIGINAL"},{"dummyselenium@gmail.com","Pass1234","IPHONE 13 PRO"}};
	}
	*/

}
