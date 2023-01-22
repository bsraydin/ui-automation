@create_session

Feature: Witwiser - Create-Session

  Scenario: Validate Successful Login
    Given I access the witwiser login page
    When I enter a username witwises-admin
    And I enter a password lLYVRayAHimpt9bJ
    And I click on the login button
    And I click on Sessions Menu
    And I click on Assign Sessions Button
    And I click on the Session Template Input Box and click on session template
    And I click on the Period Start Date and End Date
    And I click on Assignment Type ComboBox and Assigment Type
    And I click on Test Taker and enter student super admin
    And I click on Assign Session Button
    Then I should see success message