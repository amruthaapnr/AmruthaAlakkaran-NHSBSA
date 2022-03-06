package com.nhscc.helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public String getHeader(By locator) {
        return getElement(locator).getText();
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        try{
            element = driver.findElement(locator);
            return  element;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  element;
    }


    public List<WebElement> getElements(By locator) {
        List<WebElement> element = null;
        try{
            element = driver.findElements(locator);
            return  element;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  element;
    }



    public void waitForElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e){
            System.out.println("Exception occurred while waiting for the element" + locator.toString());
        }
    }


    public void waitForElementClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (Exception e){
            System.out.println("Exception occurred while waiting for the element" + locator.toString());
        }
    }


    public void waitForAllElementPresent(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        }catch (Exception e){
            System.out.println("Exception occurred while waiting for the element" + locator.toString());
        }
    }


    public void waitForTitle(String title) {
        try {
            wait.until(ExpectedConditions.titleContains(title));
        }catch (Exception e){
            System.out.println("Exception occurred while waiting for the element" + title);
        }
    }


    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getCurrentPage(){
        String url = null;
        return url = getUrl().split("/")[4].trim();
    }

    public void javaScriptExecutor(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void waitForPageLoad(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return executor.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
    }
}
