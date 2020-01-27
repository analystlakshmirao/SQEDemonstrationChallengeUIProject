package com.sample.test.demo.utilities;

public class PizzaOrderData {
	private String pizza;
	private String toppings1;
	private String toppings2;
	private String quantity;
	private String name;
	private String email;
	private String phone;
	private String paymentInfo;
	
	public PizzaOrderData(String pizza, String toppings1, String toppings2, String quantity, String nameField,
			String emailField, String phoneField, String paymentInfo) {
		this.pizza 		= pizza;
		this.toppings1 	= toppings1;
		this.toppings2 	= toppings2;
		this.quantity	= quantity;
		this.name		= nameField;
		this.email		= emailField;
		this.phone		= phoneField;
		this.paymentInfo = paymentInfo;
	}
	
	public String getPizza() {
		return pizza;
	}
	public String getToppings1() {
		return toppings1;
	}
	public String getToppings2() {
		return toppings2;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getPaymentInfo() {
		return paymentInfo;
	}
}