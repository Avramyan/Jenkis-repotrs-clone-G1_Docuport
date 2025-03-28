Feature: Validate Left Navigation items for advisor user
  @smoke
  Scenario: Validate Left nav
    Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    When user click login button
    Then validate left navigation items
      | Home            |
      | Received docs   |
      | My uploads      |
      | Clients         |
      | Invitations     |
      | Users           |
      | Leads           |
      | Bookkeeping     |
      | 1099 Form       |
      | Reconciliations |
    And user should successfully logout
