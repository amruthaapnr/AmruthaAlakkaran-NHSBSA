# AmruthaAlakkaran-NHSBSA

NHS Cost Checker Tool Automation Test Suite
==========================================================

This is a Test Automation Framework for NHS Cost Checker Tool using Cucumber, Selenium, and Java. 

Key Points
==========
Browser details are passed through VM options (chrome or fireox )
Application URL is passed through pom.xml. I added a profile named as "qa-env" in pom.xml

Executon Methods.
================
1. Commandline 

    Open the terminal and navigate to project folder . Execute below command
    **mvn clean install -Dbrowser=chrome -Pqa-env**
    
2. Eclipse/Intellij IDE

    Open maven run configuration and pass browser,profile details
    Run section  : **clean install -Dbrowser=chrome**
    profile name : **qa-env**
    
