@DeviantArt
  Feature: DeviantArt 1
    As a registered user, I want to log into DeviantArt so that I can visit my user dashboard.

    Background: Visit DeviantArt homepage
      Given I navigate to the DeviantArt homepage

    Scenario: Log into DeviantArt
      Given I log into DeviantArt
      When I click on User Menu in the page header
      Then I should be in the user dashboard for my account

    Scenario: Back back to homepage
      Given I log into DeviantArt
      When I click on User Menu in the page header
      Then I should be in the user dashboard for my account
