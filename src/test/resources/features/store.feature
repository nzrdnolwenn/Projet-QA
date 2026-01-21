@store
Feature: Apple Store
  As a customer on the Apple website
  I want to browse the Apple Store
  So that I can explore and purchase products

  Background:
    Given I am on the Apple Store page

  @smoke
  Scenario: Verify Store page loads correctly
    Then the Store page should be displayed
    And I should see product categories

  Scenario: Browse Mac products from Store
    When I click on Shop Mac
    Then I should see Mac products
    And the page should contain "Mac"

  Scenario: Browse iPhone products from Store
    When I click on Shop iPhone
    Then I should see iPhone products
    And the page should contain "iPhone"

  Scenario: Browse iPad products from Store
    When I click on Shop iPad
    Then I should see iPad products
    And the page should contain "iPad"

  Scenario: Browse Watch products from Store
    When I click on Shop Watch
    Then I should see Watch products
    And the page should contain "Watch"
