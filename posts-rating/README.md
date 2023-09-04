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

## Running application
Build development image  
`docker build -t post-api -f Dockerfile.dev .`

Starting application  
`docker run -p 3000:3000 post-api`

## Database
Some seeds are available by running  
`docker exec -it <container-name> ./bin/rails db:seed`

## Tests
Running tests  
`docker exec -it <container-name> ./bin/rspec --format d`.
