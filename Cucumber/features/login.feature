Feature: User Login

  @sanity @regression
  Scenario Outline: Successful Login
    Given the user is on the login page
    When user enters email as "<username>" and password as "<password>"
    And the user clicks on the Login button
    Then the user should be redirected to the My Account page
    And the user should see a welcome message

    Examples: 
      | username | password |
      | Admin    | admin123 |
 # Scenario: Unsuccessful Login
   #  Given the user is on the  login page
   #  When the user enters invalid credentials (username: "test@gmail.com", password: "test@12113")
    # And the user clicks on the Login button
    # Then the user should not be redirected to the My Account page
    # And the user should see alert message invalid credentials
