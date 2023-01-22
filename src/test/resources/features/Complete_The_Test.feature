@complete_the_test

Feature: Witwiser - Complete The Test

  Scenario: Complete The Test
    Given I access witwiser login page
    When I enter username witwises-admin
    And I enter password lLYVRayAHimpt9bJ
    And I click the login button
    And I click on the Start Test Button
    And I click on the Start Test
    And I answer the question one and click next button
    And I answer the question two and click next button
    And I answer the question three and click next button
    And I answer the question four and click finish button
    And I click finish test button
    Then I should see Completed Message on screen