Feature: Verify user tables names
Background:
Given Establish the database connection
@smoke
Scenario: verify users table columns
When Execute query to get all columns
Then verify the below columns are listed in result

  | id                           |
  | first_name                   |
  | last_name                    |
  | email_address                |
  | phone_number                 |
  | role_name                    |
  | supervisor_user_id           |
  | advisor_user_id              |
  | department_id                |
  | department_name              |
  | username                     |
  | inviter_user_id              |
  | is_lead_onboarding_completed |