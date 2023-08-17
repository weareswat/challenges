# README

Ruby version
    3.1.2p20

Rails version
    7.0.7


The application can be initialize normaly via command 'rails server' or via Docker:
    docker build . -t list_posts_by_rating:1
    docker run -p 3000:3000 list_posts_by_rating:1


The application permits creating, upvoting, downvoting and listing POSTS.
POSTS have UPVOTES and DOWNVOTES. POSTS are listed by their SCORE(0 to 10). 
The SCORE is the rounded perdecage between upvotes and total number of votes. 
If two POSTS have the same SCORE the one with the higher number of votes will take precedence.


There are some seeds  in seeds.rb