@search
Feature: Apple Website Search
  As a visitor to the Apple website
  I want to search for products
  So that I can find what I'm looking for quickly

  Background:
    Given I am on the Apple home page

  @smoke
  Scenario: Search for iPhone
    When I open the search
    And I search for "iPhone"
    Then I should see search results
    And the results should contain "iPhone"

  @smoke
  Scenario: Search for MacBook
    When I open the search
    And I search for "MacBook"
    Then I should see search results
    And the results should contain "MacBook"

  Scenario: Search for AirPods
    When I open the search
    And I search for "AirPods"
    Then I should see search results
    And the results should contain "AirPods"

  Scenario: Search for iPad
    When I open the search
    And I search for "iPad Pro"
    Then I should see search results
    And the results should contain "iPad"

  Scenario: Search for Apple Watch
    When I open the search
    And I search for "Apple Watch"
    Then I should see search results
    And the results should contain "Watch"

  Scenario Outline: Search for different products
    When I open the search
    And I search for "<product>"
    Then I should see search results
    And the results should contain "<expected>"

    Examples:
      | product      | expected |
      | iPhone 15    | iPhone   |
      | MacBook Air  | MacBook  |
      | iPad mini    | iPad     |
      | AirPods Pro  | AirPods  |
