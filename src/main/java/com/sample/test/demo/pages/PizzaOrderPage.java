package com.sample.test.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.test.demo.constants.Constants;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.utilities.PizzaOrderData;

public class PizzaOrderPage extends BasePage {
	WebDriver driver;

	public PizzaOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	// Locators
	private By pizza1 = By.id("pizza1Pizza");
	private By pizza1Toppings1 = By.xpath("//div[@id='pizza1']//select[@class='toppings1']");
	private By pizza1Toppings2 = By.xpath("//div[@id='pizza1']//select[@class='toppings2']");
	private By pizza1Quantity = By.id("pizza1Qty");
	private By radioCreditCard = By.id("ccpayment");
	private By radioCash = By.id("cashpayment");
	private By email = By.id("email");
	private By name = By.id("name");
	private By phone = By.id("phone");
	private By placeOrderButton = By.id("placeOrder");
	private By dialogText = By.xpath("//div[@id='dialog']/p");

	
	//Actions
	/***
     * Place Pizza Order 
     * @param pizzaOrderData - Is a POJO containing the actual pizza order fields 
     *  
     */
	public boolean order(PizzaOrderData pizzaOrderData) {
		
		sendData(pizza1, pizzaOrderData.getPizza(), Constants.PIZZA_FIELD);
		sendData(pizza1Toppings1, pizzaOrderData.getToppings1(), Constants.TOPPINGS1_FIELD);
		sendData(pizza1Toppings2, pizzaOrderData.getToppings2(), Constants.TOPPINGS2_FIELD);
		sendData(pizza1Quantity, pizzaOrderData.getQuantity(), Constants.QUANTITY_FIELD);
		sendData(name, pizzaOrderData.getName(), Constants.NAME_FIELD);
		sendData(email, pizzaOrderData.getEmail(), Constants.EMAIL_FIELD);
		sendData(phone, pizzaOrderData.getPhone(), Constants.PHONE_FIELD);
		
		selectPaymentInfo(pizzaOrderData.getPaymentInfo());
		
		elementClick(placeOrderButton, Constants.PLACE_ORDER_FIELD);
		
		String resultText = getText(dialogText, Constants.DIALOG_FIELD);
		
		//1. Verify cost
		double total = getTotal(pizzaOrderData.getPizza(),pizzaOrderData.getQuantity());
		boolean isCostVerify = verifyCost(total,resultText);
		
		//2. Verify Selected Pizza
		boolean isPizzaVerify = verifySelectedPizza(resultText,pizzaOrderData.getPizza());
		
		return isCostVerify && isPizzaVerify;	
	}
	
	/***
     * Fail Pizza Order 
     * @param pizzaOrderData - Is a POJO containing the actual pizza order fields 
     *  
     */
	public boolean failOrder(PizzaOrderData pizzaOrderData) {
		sendData(pizza1, pizzaOrderData.getPizza(), Constants.PIZZA_FIELD);
		sendData(pizza1Toppings1, pizzaOrderData.getToppings1(), Constants.TOPPINGS1_FIELD);
		sendData(pizza1Toppings2, pizzaOrderData.getToppings2(), Constants.TOPPINGS2_FIELD);
		sendData(pizza1Quantity, pizzaOrderData.getQuantity(), Constants.QUANTITY_FIELD);
		sendData(name, pizzaOrderData.getName(), Constants.NAME_FIELD);
		sendData(email, pizzaOrderData.getEmail(), Constants.EMAIL_FIELD);
		sendData(phone, pizzaOrderData.getPhone(), Constants.PHONE_FIELD);
		selectPaymentInfo(pizzaOrderData.getPaymentInfo());

		elementClick(placeOrderButton, Constants.PLACE_ORDER_FIELD);
		
		String resultMessage = getText(dialogText, Constants.DIALOG_FIELD);
		return verifyNameAndPhoneFields(resultMessage,pizzaOrderData.getName(),pizzaOrderData.getPhone());
	}
	
	/***
     * Selects the  Payment Information
     * @param paymentInfo - Either Credit Card or Cash on Pickup
     */
	private void selectPaymentInfo(String paymentInfo) {
		if(paymentInfo.equals(Constants.CREDIT_CARD)) {
			elementClick(radioCreditCard, Constants.CREDIT_CARD);
		}else {
			elementClick(radioCash, Constants.CASH);
		}
	}
	
	/***
     * Verifies if the Result message contains the actual pizza selected
     * @param resultText - Result message on placing an order
     * @param pizza - Actual pizza 
     * 
     */
	private boolean verifySelectedPizza(String resultText, String pizza) {
		if(resultText.contains(pizza))
			return true;
		return false;
	}
	
	/***
     * Verifies if the Result message contains the expected cost
     * @param resultText - Successful Placed Order message
     * @param pizza - Actual total(cost)
     * 
     */
	private boolean verifyCost(double total,String resultText) {
		if(resultText.contains(String.valueOf(total)))
			return true;
		return false;
	}
	
	/***
     * Calculates the expected cost
     * @param quantity - Selected quantity 
     * @param pizza - Selected pizza 
     * 
     */

	private double getTotal(String pizza, String quantity) {
		double cost=0;
		
		for(PizzaTypes p : PizzaTypes.values()) {
			if(p.getDisplayName().equals(pizza)) {
				cost = p.getCost();
				break;
			}
		}
		return cost * Double.parseDouble(quantity);
	}
	
	/***
     * Verifies if the Name and Phone fields are empty
     * @param resultMessage - Failed Order message 
     * @param nameField - Actual Name sent
     * @param phoneField - Actual Phone data that is sent
     * 
     */
	private boolean verifyNameAndPhoneFields(String resultMessage,String nameField,String phoneField) {
		
		if(nameField.isBlank() && phoneField.isBlank())
			return verifyNameAndPhone(resultMessage);				
		if(nameField.isBlank())
			return verifyName(resultMessage);			
		if(phoneField.isBlank())
			return verifyPhone(resultMessage);
				
		return false;
	}
	
	private boolean verifyNameAndPhone(String resultMessage) {
		if(resultMessage.contains(Constants.ERROR_MISSING_NAME) && 
				resultMessage.contains(Constants.ERROR_MISSING_PHONE)) {
			return true;
		}
		return false;
	}
	
	/***
     * Verifies if the Name field is empty
     * @param resultMessage - Failed Order message 
     *  
     */
	private boolean verifyName(String resultMessage) {
		if(resultMessage.contains(Constants.ERROR_MISSING_NAME)) {
			return true;
		}
		return false;
	}
	
	/***
     * Verifies if the Phone field is empty
     * @param resultMessage - Failed Order message 
     *  
     */
	private boolean verifyPhone(String resultMessage) {
		if(resultMessage.contains(Constants.ERROR_MISSING_PHONE)) {
			return true;
		}
		return false;
	}
}