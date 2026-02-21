Feature: Local login and signup (local profiles only)
  Automation uses only local profiles from local-profiles.properties. No external/Amazon URLs.

  Scenario: Login with local profile
    Given I am on the local login page
    When I login using the default local profile
    Then I should be on the dashboard and see I am logged in
    And I sign out from the dashboard
    Then I should be back on the local login page

  Scenario: Create account (signup) with local profile
    Given I am on the local login page
    When I go to the signup page
    And I create an account using the signup local profile
    Then I should be redirected to the login page

  Scenario Outline: Login after signup using local profile
    Given I am on the local login page
    When I login with username "<username>" and password "<password>" from local profiles
    Then I should be on the dashboard and see I am logged in

    Examples:
      | username  | password    |
      | testuser  | testpass123 |
      | newuser   | newpass123  |
