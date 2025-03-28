Feature: Docuport create new client like advisor for Personal account
  Background:
    Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    And user click login button
    And user should be able to see the home for advisor


  @newPersonalClient @docuportDb @smoke

  Scenario: crete a new personal client
    And user create a new personal client
      | First name    | Tom           |
      | Last name     | Jerry         |
      | Email address | test@test.com |
      | Phone number  | 1234567890    |
      | Password      | Password1      |
    Then validate the new client created
    And log out as an advisor
    And log as a new client
      | email    | test@test.com |
      | password | Password1      |
    And user should be able to see the home for new client
    And log out as a new client
    And create Db connection
    Then delete newly created "client"


