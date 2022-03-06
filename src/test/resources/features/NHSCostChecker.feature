@Test
Feature: Verify the users from Wales are eligible to get help for paying nhs costs

  Background:
    Given  I navigate to NHS Cost Checker application


  Scenario Outline: Verify the users from Wales are eligible to get help for paying NHS costs
    Given I start the cost checker application
    When  I enter my circumstances to cost checker tool
      |where-you-live            |<country>    |
      |gp-in-scotland-or-wales   |<gpscorwales>|
      |dental-practice-country   |<dental>     |
      |date-of-birth             |<dob>        |
      |partner                   |<partner>    |
      |claim-benefits-tax-credits|<claimbenefits>|
      |pregnant-or-given-birth   |<pregnant_or_gbirth>|
      |war-pensioner             |<warpensioner>      |
      |diabetes                  |<diabetes>          |
      |list-of-medical-conditions|<med-conditions>    |
      |glaucoma                  |<glaucoma>          |
      |care-home                 |<carehome>          |
      |savings                   |<savings>           |
      |local-council-assessed    |<councilhelp>       |
      |live-in-highlands         |<liveinhigland>     |
      |full-time-education       |<fulltimestudent>   |
    Then my eligibility for getting a help with NHS costs will be displayed on the page
      |result|<myeligibilityfornhscost>|

    Examples:
      |country |gpscorwales|dental     |dob       |partner|claimbenefits|pregnant_or_gbirth|warpensioner|diabetes|med-conditions|glaucoma|carehome|savings|councilhelp|liveinhigland|fulltimestudent|myeligibilityfornhscost  |
      |Wales   |Yes        |England    |19/01/2006| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |Yes            |result-full-time-education|
      |Wales   |No         |England    |19/01/2006| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |Yes            |result-full-time-education|
      |Wales   |No         |Wales      |19/01/2006| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |Yes            |result-full-time-education|
      |Wales   |Yes        |England    |31/07/2016| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |-              |result-under-16          |
      |Wales   |Yes        |England    |31/07/1994| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |-              |result                   |
      |Wales   |Yes        |Wales      |31/07/1994| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |-              |result                   |
      |Wales   |Yes        |Scotland   |31/07/1994| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |No           |-              |result                   |
      |Wales   |No         |England    |31/07/1994| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |-              |result                   |
      |Wales   |No         |Wales      |31/07/1994| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |-              |result                   |
      |Wales   |Yes        |England    |31/07/1994| No    |No           |No                |No          |No      |No            |No      |Yes     |No     |No         |-            |-              |result                   |
      |Wales   |Yes        |England    |31/07/1994| No    |No           |No                |No          |No      |No            |No      |Yes     |No     |Yes        |-            |-              |result-council-help-care-home |
      |Wales   |Yes        |England    |31/07/1994| No    |No           |No                |No          |No      |No            |No      |No      |Yes    |Yes        |-            |-              |result                       |
      |Wales   |No         |NI         |31/07/1994| Yes   |No           |No                |No          |No      |No            |No      |No      |Yes    |Yes        |-            |-              |result                       |
      |Wales   |Yes        |England    |31/07/2006| No    |No           |No                |No          |No      |No            |No      |No      |No     |-          |-            |-              |result-under-16              |


  Scenario: Verify the error message when an user proceed to next screen without entering the mandatory field "Which country do you live in?"
    Given I start the cost checker application
    When  I proceed to next page without selecting the "country"
    Then  I verify the error message displayed on the screen
      |error|Select the country you live in|

  Scenario: Verify the error message when an user proceed to next screen without entering the mandatory field "Is your GP practice in Scotland or Wales?"
    Given I start the cost checker application
    When  I enter my circumstances to cost checker tool
      |where-you-live            |Wales    |
    And  I proceed to next page without selecting the "GP Practice"
    Then  I verify the error message displayed on the screen
      |error|Select 'yes' if your GP practice is in Scotland or Wales|


  Scenario: Verify the error message when an user proceed to next screen without entering the mandatory field "Which country is your dental practice in?"
    Given I start the cost checker application
    When  I enter my circumstances to cost checker tool
      |where-you-live            |Wales    |
      |gp-in-scotland-or-wales   |No       |
      |dental-practice-country   |-        |
    And  I proceed to next page without selecting the "Dental Practice Country"
    Then  I verify the error message displayed on the screen
      |error|Select which country your dental practice is in|


  Scenario: Verify the error message when an user proceed to next screen without entering the mandatory field "What is your date of birth?"
    Given I start the cost checker application
    When  I enter my circumstances to cost checker tool
      |where-you-live            |Wales    |
      |gp-in-scotland-or-wales   |No       |
      |dental-practice-country   |Wales    |
      |date-of-birth             |-        |
    And  I proceed to next page without selecting the "Date of Birth"
    Then  I verify the error message displayed on the screen
      |error|Enter your date of birth|


  Scenario: Verify the error message when an user proceed to next screen without entering the mandatory field "Do you live with a partner?"
    Given I start the cost checker application
    When  I enter my circumstances to cost checker tool
      |where-you-live            |Wales    |
      |gp-in-scotland-or-wales   |No       |
      |dental-practice-country   |Wales    |
      |date-of-birth             |29/06/1993|
      |partner                   |         |
    And  I proceed to next page without selecting the "Partner"
    Then  I verify the error message displayed on the screen
      |error|Select 'yes' if you live with a partner|
