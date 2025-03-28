Feature: As a data consumer, I want to make sure the LEADS information from UI are stored in postgres DB correctly in LEADS table.

  @ui @docuportDb @leads @smoke

  Scenario: verify UI leads matches in DB
    Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    And user click login button
    And the user navigates to "Leads" page
    When the user gets all the leads information based on "Full name" and "Email address" and "Phone number"
    Then verify leads information match in DB
