Feature: Advisor Cannot Create Client Without Required Fields

  @AA @ui @smoke
  Scenario Outline: As Advisor create a new <clientType> client with empty first and last name inputs

    Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    And user click login button
    Then user should be able to see the home for advisor
    When user clicks Clients on left navigation Page
    Then user should see Clients page

    When  user clicks Create new client dropdown and choose "<clientType>"
    And create new client window popped up user clicks Create new user checkbox
    Then user fills out all required fields except first and last name for "<clientType>"
      | Email            | <email> |
      | Advisor          | advisor advisor    |
      | Phone number     | 3109752607         |
      | Password         | LolaGladiola1      |
      | Confirm Password | LolaGladiola1      |
      | Company name     | Big Deals          |
    And user clicks save
    Then an error message should be displayed
    And validate that user was not created
      | <email> |
    Examples:
      | clientType |email|
      | Business   |l.kramer@gmail.com|
      | Personal   |k.glad@gmail.com  |