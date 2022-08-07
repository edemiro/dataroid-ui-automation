Feature: Verify The Latest News list

  Scenario: Verify that each news has an author and images

    Given The user goes to TestCrunch website
    Then Check The Latest News is displayed
    And Check each news has an author
    And Check each news has an image
    When The user chooses one of the news on The Latest News
    Then Check the browser title and full content title is same
    And Check the links of the content





