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
## (Comments)
@EmployeePersistence
Feature: The Employee can be persisted/retrieved
  I want save/retrieve employee details into persistence store

  @EmployeePersistence1
  Scenario: The Employee can persist/retrieved through end points
    Given the department SALES exist in system :
    Then I called /post endpoint to save employee HARRY in SALES department :
    Then I called /get/HARRY endpoint to get all employees and I received name and department as : HARRY,SALES
