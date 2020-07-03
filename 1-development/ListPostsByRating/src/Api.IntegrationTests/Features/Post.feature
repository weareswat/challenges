Feature: Post

Scenario: Up vote a post
    Given there is a set of posts
    And I have a post with id 1
    And it contains 0 up votes
    When I up vote the post
    Then the post should have 1 up vote

Scenario: Down vote a post
    Given there is a set of posts
    And I have a post with id 2
    And it contains 3 down votes
    When I down vote the post
    Then the post should have 4 down votes

Scenario: List posts by rating
    Given there is a set of posts
    And I have a post with id 1
    And it contains 600 up votes
    And it contains 400 down votes
    And I have a post with id 2
    And it contains 6 up votes
    And it contains 4 down votes
    When I get a list of all posts
    Then the first post id should be 1
    And the second post id should be 2
