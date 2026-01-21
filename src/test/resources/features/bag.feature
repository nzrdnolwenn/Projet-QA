@bag
Feature: Shopping Bag
  As a customer on the Apple website
  I want to manage my shopping bag
  So that I can review items before checkout

  @smoke
  Scenario: View empty shopping bag
    Given I am on the Apple home page
    When I open the shopping bag
    Then I should see the bag page
    And the bag should be empty

  Scenario: Navigate from bag to Store
    Given I am on the shopping bag page
    When I click continue shopping
    Then I should be redirected to the Store
