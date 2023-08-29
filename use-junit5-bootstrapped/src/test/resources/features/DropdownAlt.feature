@DropdownAlt
Feature: The Internet - Dropdown List
  As a user, I want navigate to the Dropdown page and see a dropdown.

  Background: Visit The Internet on Heroku
    Given I navigate to "http://the-internet.herokuapp.com/"

  Scenario: Click on Dropdown
    When I click on "Dropdown"
    And wait 5 seconds
    Then I should see "Dropdown List"


  Scenario: Click on Dropdown 2
    When I click on "Dropdown"
    And wait 5 seconds
    Then I should see "Dropdown List"