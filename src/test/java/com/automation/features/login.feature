#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)#Sample Feature Definition Template
#@skip_scenario
#@close_browser
Feature: Login
  Scenario Outline: To verify wether we are able to sign in using newtours credentials
   Given URL for newtours domain website
   When we hit the URL and get the newtours page
    When I enter my <name> and <pass> and click on sign in
    Then I verify the the title  <status> in refrence domain home page

    Examples: 
     | name  | pass | status |
     | shettygaurav86@gmail.com | Great*123 | SIGN-OF |
