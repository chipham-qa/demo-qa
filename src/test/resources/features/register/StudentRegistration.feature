Feature: Register
  As a student
  I want to submit a form
  Then my information submitted successfully

  Background:
    Given user navigates to the register page

  @register
  Scenario: Submit register form successfully

    When user fills out the registration form with the following details
    | First name | Last name | Email              | Gender | Mobile     | Date Of Birth     | Subjects | Hobbies | Picture      | Address                 | State  | City |
    | Alice      | Firstimer | chi.pham@gmail.com | Male   | 0977771111  | 7-September-1996  | Maths    | Sports   | Avatar.png  | 2 Nguyen Thi Minh Khai  | NCR   | Delhi |
    Then page title should display "Thanks for submitting the form" upon form submission
    And submitted user info should match the following
      | Student Name     | Student Email      | Gender | Mobile     | DOB               | Subjects | Hobbies | Picture     | Address                 | State and City |
      | Alice Firstimer  | chi.pham@gmail.com | Male   | 0977771111 | 07 September,1996 | Maths    | Sports  | Avatar.png  | 2 Nguyen Thi Minh Khai  | NCR Delhi      |

  @register
  Scenario: Mobile field should be red when user does not input the value
    When user fills out the registration form with the following details
      | First name | Last name | Email              | Gender | Mobile     | Date Of Birth     | Subjects | Hobbies | Picture      | Address                 | State  | City |
      | Alice      | Firstimer | chi.pham@gmail.com | Male   |            | 7-September-1996  | Maths    | Sports   | Avatar.png  | 2 Nguyen Thi Minh Khai  | NCR   | Delhi |
    Then "Mobile" field should be red