
@smoke
Feature: Validate Left Navigation items for client

  Scenario: Scenario: Validate Left nav
    Given user is on Docuport login page
    When user enters username for client
    And user enters password for client
    And user click login button
    Then validate left navigation items
      | Home            |
      | Received docs   |
      | My uploads      |
      | Invitations     |

