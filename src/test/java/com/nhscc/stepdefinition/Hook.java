package com.nhscc.stepdefinition;


import com.nhscc.helper.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hook {

    private BrowserManager browserManager;
    private WebDriver driver;

    @Before
    public void TestInitialize(){
        browserManager = new BrowserManager();
        driver = browserManager.createBrowser(System.getProperty("browser"));
    }

    @After(order =0)
    public void TestCleanup(){
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @After(order =1)
    public void takeScreenshot(Scenario scenario){
        if(scenario.isFailed()){
            String screenshotName= scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
}
