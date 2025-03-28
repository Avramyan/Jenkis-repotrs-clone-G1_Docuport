Feature: Docuport Login Logout Feature


  Background: this is for navigating Docuport login page+
    Given user is on Docuport login page

  @smoke @ZZ
  Scenario: Login as a client
    When user enters username for client
    And user enters password for client
    And user click login button
    Then user should be able to see the home for client
    And user should successfully logout

  @smoke
  Scenario: Login as a employee
    When user enters username for employee
    And user enters password for employee
    And user click login button
    Then user should be able to see the home for employee
    And user should successfully logout

  @smoke
  Scenario: Login as a advisor
    When user enters username for advisor
    And user enters password for advisor
    And user click login button
    Then user should be able to see the home for advisor
    And user should successfully logout

  @smoke
  Scenario: Login as a supervisor
    When user enters username for supervisor
    And user enters password for supervisor
    And user click login button
    Then user should be able to see the home for supervisor
    And user should successfully logout