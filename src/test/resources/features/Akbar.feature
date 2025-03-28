Feature: As a data consumer, I want UI account holder name to be in DB.
@ui @docuportDb @smoke
Scenario: verify UI user total account count matches in DB
  Given the "advisor" on the home page
  And the user navigates to "Users" page
  When the user gets total user count
  Then verify user count information match in DB