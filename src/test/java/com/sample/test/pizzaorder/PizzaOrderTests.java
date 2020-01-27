package com.sample.test.pizzaorder;



import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.Constants;
import com.sample.test.demo.pages.PizzaOrderPage;
import com.sample.test.demo.utilities.ExcelUtility;
import com.sample.test.demo.utilities.PizzaOrderData;

public class PizzaOrderTests extends TestBase{
	
	@BeforeClass
    public void setUp() {
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "PizzaOrderTests");
    }
	
	 @DataProvider(name = "testSuccessPizzaOrderData")
	    public Object[][] getSuccessfulPizzaOrderData(){
	        Object[][] testData = ExcelUtility.getTestData("testSuccessPizzaOrders");
	        return testData;
	    }
	 
	 @DataProvider(name = "testFailedPizzaOrderData")
	    public Object[][] getFailedPizzaOrderData(){
	        Object[][] testData = ExcelUtility.getTestData("testFailedPizzaOrders");
	        return testData;
	    }
	
	@Test(dataProvider="testSuccessPizzaOrderData",priority=0)
	public void testSuccessPizzaOrders(String pizza1,String toppings1,String toppings2,String quantity,
			String name,String email, String phone, String paymentInfo) {
		PizzaOrderPage pizza 		  = new PizzaOrderPage(driver);
		PizzaOrderData pizzaOrderData = new PizzaOrderData(pizza1,toppings1,toppings2,quantity,name,email,phone,paymentInfo);
		boolean result = pizza.order(pizzaOrderData);
		assertTrue(result, "Failed Pizza Order");
	}
	
	@Test(dataProvider="testFailedPizzaOrderData",priority=1)
	public void testFailedPizzaOrders(String pizza1,String toppings1,String toppings2,String quantity,
			String name,String email, String phone, String paymentInfo) {
		PizzaOrderPage pizza 		  = new PizzaOrderPage(driver);
		PizzaOrderData pizzaOrderData = new PizzaOrderData(pizza1,toppings1,toppings2,quantity,name,email,phone,paymentInfo);
		boolean result = pizza.failOrder(pizzaOrderData);
		
		assertTrue(result, "Failed Pizza Order : Missing name or Phone");
	}
}