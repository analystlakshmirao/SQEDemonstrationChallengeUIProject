package com.sample.test.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
    
    public void sendData(By byType,String data,String info) {
    	sendData(byType, data, info, true);
    }
    
    public void sendData(By byType, String data, String info, Boolean clear) {
        WebElement element = this.getElement(byType, info);
        sendData(element, data, info, clear);
    }
   
    public WebElement getElement(By byType, String info) {
        WebElement element = null;
        try {
            element = driver.findElement(byType);
        } catch (Exception e) {
            System.out.println("Element not found with: " + byType);
            e.printStackTrace();
        }
        return element;
    }
   
	/***
     * Send Keys to element
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
            	if(!(element.getTagName().equals("select"))){
            		element.clear();
            	}
            }
            element.sendKeys(data);
            System.out.println("Send Keys on element :: "
                    + info + " with data :: " + data);
        } catch (Exception e) {
            System.out.println("Cannot send keys on element :: "
                    + info + " with data :: " + data);
        }
    }

     /**
     * Click element with information about element and
     * time to wait in seconds after click
     *
     * @param element - WebElement to perform Click operation
     * @param info    - information about element
     */
    public void elementClick(WebElement element, String info, long timeToWait) {
        try {
            element.click();
            if (timeToWait == 0) {
                System.out.println("Clicked On :: " + info);
            } 
        } catch (Exception e) {
            System.out.println("Cannot click on :: " + info);
        }
    }

    
    public void elementClick(By byType, String info) {
        WebElement element = getElement(byType, info);
        elementClick(element, info, 0);
    }
    
    
    public void elementClick(By byType, String info, long timeToWait) {
        WebElement element = this.getElement(byType, info);
        elementClick(element, info, timeToWait);
    }
    
    /**
     * Get text of a web element
     *
     * @param element - WebElement to perform click action
     * @param info    - Information about element
     */
    public String getText(WebElement element, String info) {
        System.out.println("Getting Text on element :: " + info);
        String text = null;
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
            System.out.println(" The text is : " + text);
        } else {
            text = "NOT_FOUND";
        }
        return text.trim();
    }

    /**
     * Get text of a web element with byType
     * @param byType
     * @param info
     * @return
     */
    public String getText(By byType, String info) {
        WebElement element = this.getElement(byType, info);
        return this.getText(element, info);
    }

}