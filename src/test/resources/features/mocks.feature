@mocks
Feature: Mock Scenarios
  Demonstrate network mocking for edge cases and error handling

  @smoke
  Scenario: Test with mocked slow network
    Given I am on the Apple home page with slow network simulation
    Then the page should still load successfully

  Scenario: Test navigation with network interception
    Given I am on the Apple home page with network logging enabled
    When I navigate to the iPhone page
    Then network requests should be captured

  Scenario: Simulate API error response
    Given I set up a mock for 500 server error on bag API
    When I try to access the bag
    Then the page should handle the error gracefully
