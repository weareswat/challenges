Feature: Post

Scenario: Up vote a post
    Given I have a post with id 1
    And it contains 0 up votes
    When I up vote the post
    Then the post should have 1 up vote

Scenario: Down vote a post
    Given I have a post with id 2
    And it contains 3 down votes
    When I down vote the post
    Then the post should have 4 down votes

