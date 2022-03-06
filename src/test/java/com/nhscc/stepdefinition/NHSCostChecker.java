package com.nhscc.stepdefinition;


import com.nhscc.helper.BrowserManager;
import com.nhscc.pages.NHSCostCheckerConstants;
import com.nhscc.pages.NHSCostCheckerPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class NHSCostChecker {

    private NHSCostCheckerPage nhscostCheckerPage = new NHSCostCheckerPage(BrowserManager.getBrowser());


    @Given("I navigate to NHS Cost Checker application")
    public void i_navigate_to_nhs_cost_checker_application() {
        nhscostCheckerPage.openNHSCostCheckerApplication();
        nhscostCheckerPage.waitForStartButtonClickable();
        nhscostCheckerPage.DoNotAcceptsCookies();
    }

    @Given("I start the cost checker application")
    public void i_start_the_nhs_cost_checker_application() {
        nhscostCheckerPage.waitForStartButtonClickable();
        nhscostCheckerPage.clickStart();
    }

    @When("^I proceed to next page without selecting the \"([^\"]*)\"$")
    public void i_proceed_to_next_page_without_selecting_the_value(String fieldName) {
        nhscostCheckerPage.waitForStartButtonClickable();
        nhscostCheckerPage.clickStart();
    }

    @Then("I verify the error message displayed on the screen")
    public void i_verify_the_validation_message(Map<String, String> errorMessage) {
        Assert.assertEquals(NHSCostCheckerConstants.ERROR_MESSAGE, nhscostCheckerPage.getHeaderErrorMessageText());
        Assert.assertEquals(errorMessage.get("error"), nhscostCheckerPage.getTopErrorMessageText());
        Assert.assertEquals(errorMessage.get("error"), nhscostCheckerPage.getBottomErrorMessageText());
    }

    @When("I enter my circumstances to cost checker tool")
    public void i_enter_my_circumstances_to_cost_checker_tool(Map<String, String> myDetailsTable) {
        String currentPage = null;
        String page = null;
        String inputValue = null;
        while (!nhscostCheckerPage.getCurrentPage().startsWith("result")) {
            nhscostCheckerPage.waitForPageLoad();
            currentPage = nhscostCheckerPage.getCurrentPage();

            switch (currentPage) {
                case NHSCostCheckerConstants.WHERE_YOU_LIVE:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectCountry(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.COUNTRY_Q, nhscostCheckerPage.getQuestionText());
                    Assert.assertTrue(nhscostCheckerPage.isCountrySelected(inputValue));
                    break;
                case NHSCostCheckerConstants.GP_IN_SCOTLAND_OR_WALES:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.GP_PRACTICE_Q, nhscostCheckerPage.getQuestionText());
                    break;

                case NHSCostCheckerConstants.DENTAL_PRACTICE_COUNTRY:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectDentalPracticeCountry(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.DENTAL_PRACTICE_COUNTRY_Q, nhscostCheckerPage.getQuestionText());
                    Assert.assertTrue(nhscostCheckerPage.isDentalPracticeCountrySelected(inputValue));
                    break;

                case NHSCostCheckerConstants.DATE_OF_BIRTH:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.enterDateOfBirth(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.DOB_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.PARTNER:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.PARTNER_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.CLAIM_BENEFITS_TAX_CREDITS:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    if (myDetailsTable.get(NHSCostCheckerConstants.PARTNER).equalsIgnoreCase("Yes")) {
                        Assert.assertEquals(NHSCostCheckerConstants.CLAIM_YOU_PART_Q, nhscostCheckerPage.getQuestionText());
                    } else {
                        Assert.assertEquals(NHSCostCheckerConstants.CLAIM_YOU_Q, nhscostCheckerPage.getQuestionText());
                    }

                    break;
                case NHSCostCheckerConstants.PREGNANT_OR_GIVEN_BIRTH:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.PREGNANT_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.WAR_PENSIONER:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.PENSIONER_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.DIABETES:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.DIABETES_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.LIST_OF_MEDICAL_CONDITIONS:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.MED_CONDITION_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.GLAUCOMA:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.GLAUCOMA_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.CARE_HOME:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    if (myDetailsTable.get(NHSCostCheckerConstants.PARTNER).equalsIgnoreCase("Yes")) {
                        Assert.assertEquals(NHSCostCheckerConstants.CARE_HOME_PARTNER_Q, nhscostCheckerPage.getQuestionText());
                    } else {
                        Assert.assertEquals(NHSCostCheckerConstants.CARE_HOME_Q, nhscostCheckerPage.getQuestionText());
                    }

                    break;
                case NHSCostCheckerConstants.SAVINGS:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    if (myDetailsTable.get(NHSCostCheckerConstants.CARE_HOME).equalsIgnoreCase("Yes")) {
                        Assert.assertEquals(nhscostCheckerPage.removeSpecialCharacters(NHSCostCheckerConstants.SAVINGS_1_Q), nhscostCheckerPage.removeSpecialCharacters(nhscostCheckerPage.getQuestionText()));
                    } else if (myDetailsTable.get(NHSCostCheckerConstants.PARTNER).equalsIgnoreCase("Yes")) {
                        Assert.assertEquals(nhscostCheckerPage.removeSpecialCharacters(NHSCostCheckerConstants.SAVINGS_2_PARTNER_Q), nhscostCheckerPage.removeSpecialCharacters(nhscostCheckerPage.getQuestionText()));
                    } else {
                        Assert.assertEquals(nhscostCheckerPage.removeSpecialCharacters(NHSCostCheckerConstants.SAVINGS_2_Q), nhscostCheckerPage.removeSpecialCharacters(nhscostCheckerPage.getQuestionText()));
                    }
                    break;
                case NHSCostCheckerConstants.LOCAL_COUNCIL_ASSESSED:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.COUNCIL_Q, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.LIVE_IN_HIGHLANDS:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.LIVE_IN_HIGHLAND, nhscostCheckerPage.getQuestionText());
                    break;
                case NHSCostCheckerConstants.FULL_TIME_EDUCATION:
                    inputValue = myDetailsTable.get(currentPage);
                    nhscostCheckerPage.selectYesOrNo(inputValue);
                    Assert.assertEquals(NHSCostCheckerConstants.FULL_TIME_EDUCATION_Q, nhscostCheckerPage.getQuestionText());
                    break;

            }
            nhscostCheckerPage.clickStart();
            page = nhscostCheckerPage.getCurrentPage();

            if (page.equalsIgnoreCase(currentPage)) {
                break;
            }
        }
    }

    @Then("my eligibility for getting a help with NHS costs will be displayed on the page")
    public void my_Eligibility_For_Getting_AHelp_With_NHSCosts_WillBe_Displayed_OnThePage(Map<String, String> myResultTable) {
        String currentPage = nhscostCheckerPage.getCurrentPage();
        String expectedResultPage = myResultTable.get("result");
        switch (currentPage){
            case NHSCostCheckerConstants.RESULT:
                Assert.assertEquals("Expected and actual done page are not same",expectedResultPage, currentPage);
                Assert.assertEquals("My eligibility for getting help with NHS costs is not matching with expected",NHSCostCheckerConstants.RESULT_GET_HELP, nhscostCheckerPage.getResult());
                break;
            case NHSCostCheckerConstants.RESULT_COUNCIL_HELP_CARE_HOME:
                Assert.assertEquals("Expected and actual done page are not same",expectedResultPage, currentPage);
                Assert.assertEquals("My eligibility for getting help with NHS costs is not matching with expected",NHSCostCheckerConstants.RESULT_CAN_APPLY_FOR_HELP, nhscostCheckerPage.getResultCouncilHelp());
                break;
            case NHSCostCheckerConstants.RESULT_UNDER_16:
                Assert.assertEquals("Expected and actual done page are not same",expectedResultPage, currentPage);
                Assert.assertEquals("My eligibility for getting help with NHS costs is not matching with expected",NHSCostCheckerConstants.RESULT_GET_HELP, nhscostCheckerPage.getResultCouncilHelp());
                break;
            case NHSCostCheckerConstants.RESULT_FULL_TIME_EDUCATION:
                Assert.assertEquals("Expected and actual done page are not same",expectedResultPage, currentPage);
                Assert.assertEquals("My eligibility for getting help with NHS costs is not matching with expected",NHSCostCheckerConstants.RESULT_GET_HELP, nhscostCheckerPage.getResultCouncilHelp());
                break;
        }

    }
}
