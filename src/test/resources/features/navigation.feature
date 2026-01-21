@navigation
Feature: Apple Website Navigation
  As a visitor to the Apple website
  I want to navigate through different sections
  So that I can explore Apple products

  Background:
    Given I am on the Apple home page

  @smoke
  Scenario: Verify home page loads correctly
    Then the navigation bar should be visible
    And the page title should contain "Apple"

  @smoke
  Scenario: Navigate to Store page
    When I click on the Store link in navigation
    Then I should be on the Store page
    And the Store page should display product categories

  @smoke
  Scenario: Navigate to iPhone page
    When I click on the iPhone link in navigation
    Then I should be on the iPhone page
    And the page should display iPhone product information

  @smoke
  Scenario: Navigate to Mac page
    When I click on the Mac link in navigation
    Then I should be on the Mac page
    And the page should display Mac product information

  Scenario: Navigate to iPad page
    When I click on the iPad link in navigation
    Then I should be on the iPad page
    And the page should display iPad product information

  Scenario: Navigate to Watch page
    When I click on the Watch link in navigation
    Then I should be on the Watch page
    And the page should display Watch product information

  Scenario: Navigate to AirPods page
    When I click on the AirPods link in navigation
    Then I should be on the AirPods page
    And the page should display AirPods product information

  Scenario Outline: Navigate to different product categories
    When I click on the "<category>" link in navigation
    Then I should be on the "<category>" page
    And the page title should contain "<category>"

    Examples:
      | category |
      | iPhone   |
      | Mac      |
      | iPad     |
      | Watch    |
