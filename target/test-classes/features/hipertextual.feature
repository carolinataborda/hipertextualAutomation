Feature: Search and Subscribe to Hipertextual Newsletter

  Background:
    Given the user wants to navigate to "https://hipertextual.com"

  Scenario: Search for Steve Jobs and navigate to first post
    When he search for "Steve Jobs"
    Then he validate the URL contains "https://hipertextual.com"
    And he validate the page contains the text "hipertextual"

  Scenario: Subscribe to the newsletter
    Given he wants to subscribe to the newsletter
    When he clicks and enter the email "testExample@mail.com"
    Then he should see a confirmation message
