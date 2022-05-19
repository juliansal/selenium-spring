@TheInternet
  Feature: The Internet - Form Authentication
    As a user, I want to log into the Login Page for The Internet and see if my credential work.

  Background: Visit The Internet on Heroku
    Given I navigate to "http://the-internet.herokuapp.com/"

    Scenario: Log into the Form Authentication
      Given I click on "Form Authentication"
      When I log in using credentials:
      | username  | password              |
      | tomsmith  | SuperSecretPassword!  |
      Then I should see "Secure Area"
      And I should see "Welcome to the Secure Area."