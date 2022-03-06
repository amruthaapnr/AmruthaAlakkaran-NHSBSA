package com.nhscc.pages;

import com.nhscc.helper.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NHSCostCheckerPage extends BasePage {

    private WebDriver driver;

    private By startOrNextButton = By.id("next-button");
    private By doNotAcceptsCookies = By.id("nhsuk-cookie-banner__link_accept");
    private By countryRadioButton = By.name("livingCountry");
    private By dentalPracticeCountryRadioButton = By.name("dentalPracticeCountry");
    private By yesOrNoRadioButton = By.xpath("//input[@type='radio']");
    private By dayDob = By.id("dob-day");
    private By monthDob = By.id("dob-month");
    private By yearDob = By.id("dob-year");
    private By result = By.id("result-heading");
    private By question = By.id("question-heading");
    private By yesOrNoRadioLabel = By.xpath("//label[@class='block-label selection-button-radio']");
    private By resultCouncil = By.xpath("//h2[@class='heading-large']");
    private By errorMessageHeader = By.id("error-summary-heading");
    private By errormessageTop = By.xpath("//li[@class='error-message']");
    private By errormessageBottom = By.xpath("//span[@class='error-message']");

    public NHSCostCheckerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<WebElement> getCountryRadioButton() {
        return getElements(countryRadioButton);
    }

    public List<WebElement> getDentalPracticeCountryRadioButton() {
        return getElements(dentalPracticeCountryRadioButton);
    }

    public WebElement getResultElement() {
        return getElement(result);
    }

    public WebElement getDateOfBirthDay() {
        return getElement(dayDob);
    }

    public WebElement getDateOfBirthMonth() {
        return getElement(monthDob);
    }

    public WebElement getDateOfBirthYear() {
        return getElement(yearDob);
    }

    public WebElement getQuestion() {
        return getElement(question);
    }

    public WebElement getDoNotAcceptsCookies() {
        return getElement(doNotAcceptsCookies);
    }

    public WebElement getStartOrNextButton() {
        return getElement(startOrNextButton);
    }

    public  void openNHSCostCheckerApplication(){
        navigateToUrl(System.getProperty("url"));
    }

    public WebElement getCouncilResult() {
        return getElement(resultCouncil);
    }

    public WebElement getHeaderErrorMessage() {
        return getElement(errorMessageHeader);
    }

    public WebElement getTopErrorMessage() {
        return getElement(errormessageTop);
    }

    public WebElement getBottomErrorMessage() {
        return getElement(errormessageBottom);
    }


    public List<WebElement> getYesOrNoRadioLabel(){
        return getElements(yesOrNoRadioLabel);
    }

    public List<WebElement> getYesOrNoRadioButton() {
        return getElements(yesOrNoRadioButton);
    }

    public String getHeaderErrorMessageText(){
        return getHeaderErrorMessage().getText();
    }

    public String getTopErrorMessageText(){
        return getTopErrorMessage().getText();
    }

    public String getBottomErrorMessageText(){
        return getBottomErrorMessage().getText();
    }


    public void waitForStartButtonClickable(){
        waitForElementPresent(startOrNextButton);
        waitForElementClickable(startOrNextButton);
    }

    public void clickStart(){
        waitForStartButtonClickable();
        getStartOrNextButton().click();
    }

    public void clickNext(){
        waitForStartButtonClickable();
        getStartOrNextButton().click();
    }

    public void DoNotAcceptsCookies(){
        getDoNotAcceptsCookies().click();
    }

    public String removeSpecialCharacters(String value){
        return value.replaceAll("[-+.Â£?^:]","").trim();
    }

    public void selectCountry(String countryName) {

        String value = null;
        if (countryName.equalsIgnoreCase("NI")){
            value="Northern Ireland";
        }else{
            value= countryName;
        }
        List<WebElement> countries= null;
        waitForAllElementPresent(countryRadioButton);
        countries=  getCountryRadioButton();
        for  (WebElement country : countries) {
            if (country.getAttribute("value").equalsIgnoreCase(value)){
                javaScriptExecutor(country);
            }
         }
    }

    public void selectDentalPracticeCountry(String countryName) {

        String value = null;
        if (countryName.equalsIgnoreCase("NI")){
            value="Northern Ireland";
        }else{
            value= countryName;
        }
        List<WebElement> countries= null;
        waitForAllElementPresent(dentalPracticeCountryRadioButton);
        countries=  getDentalPracticeCountryRadioButton();
        for  (WebElement country : countries) {
            if (country.getAttribute("value").equalsIgnoreCase(value)){
                javaScriptExecutor(country);
            }
        }

    }

  public String getQuestionText(){
        return getQuestion().getText();
    }

    public boolean isCountrySelected(String countryName){
        if (countryName.equalsIgnoreCase("NI")){
            countryName="Northern Ireland";
        }
        List<WebElement> countries= null;
        waitForAllElementPresent(countryRadioButton);
        countries=  getCountryRadioButton();
        for  (WebElement country : countries) {

            if (country.getAttribute("value").equalsIgnoreCase(countryName)){
               return country.isSelected();
            }
        }
        return false;
    }

    public boolean isDentalPracticeCountrySelected(String countryName){
        if (!countryName.equalsIgnoreCase("-")) {
            if (countryName.equalsIgnoreCase("NI")) {
                countryName = "Northern_Ireland";
            }
            List<WebElement> countries = null;
            waitForAllElementPresent(dentalPracticeCountryRadioButton);
            countries = getDentalPracticeCountryRadioButton();
            for (WebElement country : countries) {
                if (country.getAttribute("value").equalsIgnoreCase(countryName)) {
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }

    public String getResult() {
        return getResultElement().getText().split("\n")[1].trim();
    }

    public String getResultCouncilHelp() {
        return getCouncilResult().getText().split("\n")[1].trim();
    }

    public  void enterDateOfBirth(String dateofbirth){
        if (!dateofbirth.equals("-")){
            String[] date =dateofbirth.split("/");
            getDateOfBirthDay().sendKeys(date[0]);
            getDateOfBirthMonth().sendKeys(date[1]);
            getDateOfBirthYear().sendKeys(date[2]);
        }
    }

    public void selectYesOrNo(String value){
        List<WebElement> elements= null;
        waitForAllElementPresent(yesOrNoRadioButton);
        elements=  getYesOrNoRadioButton();
        for  (WebElement element : elements) {
            if (element.getAttribute("value").equalsIgnoreCase(value)){
                javaScriptExecutor(element);
            }
        }
    }
}
