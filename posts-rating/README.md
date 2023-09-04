# Posts API
API to create posts and allow users rate them.

## Features

- create posts  
  POST /posts

- update posts  
  PUT /posts/:id  
  PATCH /posts/:id

- delete posts  
  DELETE /posts/:id  

- list posts by score in order to votes ratio  
  GET /posts  

- list specific post  
  GET /posts/:id  

- rate posts as up or down  
  POST /upvote/:post_id  
  POST /downvote/:post_id  

## Setup
`bin/setup`.

## Database
Some seeds are available running: `bin/rails db:seed`

## Tests
`./bin/rspec --format d`.
