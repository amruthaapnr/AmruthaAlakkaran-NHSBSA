package com.nhscc.helper;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserManager {
    public WebDriver driver;

    public static  ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public WebDriver createBrowser(String browser){
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("profile.default_content_setting_values.cookies", 2);
                chromeOptions.setCapability("network.cookie.cookieBehavior", 2);
                drivers.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                drivers.set(new FirefoxDriver());
                break;
        }
        getBrowser().manage().deleteAllCookies();
        getBrowser().manage().window().maximize();
        return getBrowser();
    }

    public static WebDriver getBrowser(){
        return  drivers.get();
    }
}
