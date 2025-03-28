Feature: Click buttons advisor

  @smoke
  Scenario: Click buttons on different pages as a client
    Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    And user click login button
    Then user should be able to see the home for client
    And user clicks "Received Doc" button on "Left Navigate" page
    And user clicks "Search" button on "Received Doc" page
