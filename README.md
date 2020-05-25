# List Posts by Rating 

This is my solution for the 'List Post by Rating' challenge.

## Problem

You are a web programmer. You have users. Your users rate posts on your site. You want to put the highest-rated posts at the top and lowest-rated at the bottom. You need some sort of "score" to sort by.

### Objectives

We'd like to see a working web service with the following endpoints:

```
/upvote/:post_id
/downvote/:post_id
/posts/
```

No UI is required. You can use a database of your choice.

Tech stack: Feel free to use the stack that you feel more comfortable.This means you can choose any language you need to get the job done.

Your solution should consider that if you have a first post with 600 up votes and 400 down votes means that you have 60% of up votes and 40% of down votes, and if you have another post with 6 up votes and 4 down votes you have the same % of the previous post, 60% of up votes and 40% of down votes.

Hint: Note that the score in % of the two posts are equal, but the real "values" are significantly differents

You should be doing the solution on a specific branch. Once you have something to delivery, you can open a Pull Request. You can use the Pull Request if you'd like some feedback on your code or to discuss something with us.

### Things We value

- Clean code, we want you to show us the best code you can do
- If you are familiar with TDD, BDD or any testing process, please show us your skills

## Solution

My solution is a Node.js service exposing an API on port `8080` with the following endpoints:
- `GET /posts` 
    - returns a list of posts in the expected order in JSON format
- `GET /posts/:post_id` 
    - returns the post with the specified ID
    - returns error if post with specified ID is not found
- `POST /posts` 
    - create new post
    - expects parameter `title` in body
    - returns the newly created post
- `PUT /upvote/:post_id` or `PUT /posts/:post_id/upvote`
    - upvote specified post
- `PUT /downvote/:post_id` or `PUT /posts/:post_id/downvote`
    - downvote specified post

### Technologies

The following are the main technologies used:
- Node.js
- TypeScript
- Express
- Joi
- Knex
- Jest
- PostgreSQL
- Docker

### Decisions and Assumptions

Some decisions were made regarding architecture, database schema or functionality during the development of the solution. This is a list of the most notable ones.

#### User authentication is out of scope

I felt user authentication was out of scope of this challenge but since the challenge mentions my application has users I assumed all endpoints required the user to be authenticated to access. In order to simulate this all endpoints require the presence of a header named `rupeal-user` which represents the user accessing the endpoint. If this header is not present the service returns `401` error.

#### One vote per user

Since the app has users I assumed each user would only be able to cast one vote per post, be it up or down. Votes can be changed but they can't be taken back.

#### Posts order

When the list posts endpoint is called, the posts are ordered according to the following rules (in order of priority):
1. Upvote percentage, descending
2. Downvote percentage, ascending
3. Total votes, descending
4. Date created, descending

This gives us the following:
- Posts with larger upvote percentage appear higher on the list
- Posts with the more total votes appear higher than posts with the less votes but the same upvote percentage
- Post with no votes appear higher than posts with 100% downvotes
- If all else fails posts are ordered from most recent to oldest

#### Database schema

The database schema is composed of two tables: `posts` and `votes`. The latter keeps a record of how a certain user voted on a certain post. While the former keeps all the information for each post along with how many votes it has as well as upvote and downvote percentages.

Please check the migrations folder to get a complete understanding of the schema.

#### Maximizing read speed

Since the list posts endpoint would likely be visited the most in a real-world scenario, I decided to structure the code and databse in a way that would allow me to respond to this request as fast as possible. This meant sacrificing performance for the upvote and downvote endpoints but I feel this is a fair trade-off.

As mentioned above the `posts` table has information regarding the votes for each post. This means that getting a list of posts ordered in the correct manner is one simple query away. As such all the work for the list posts endpoint is done by the DB essentially.

The downside of this approach is that the upvote and downvote endpoints end up being slower. They need to write to the `votes` table (in order to make sure the user isn't casting multiple votes per post), followed by calculating the number of votes and percentages and writing it all to the `posts` table. This whole operation is done in a transaction.

An alternative approach would be to perform a slower JOIN query when listing posts and having a simpler logic in the upvote/downvote endpoints.

I feel like the current approach is a fair trade-off for two reasons:
1. the list posts endpoint can be as fast as possible
2. in most websites upvote/downvote operation is optimistic on FE side, which means the FE wouldn't need to wait for the response from the endpoints
3. if upvote/downvote performance ever becomes a problem (e.g. too many concurrent votes) it would be rather simple to modify the app's behaviour to use the alternative approach mentioned above

### Possible improvements

While the code has Unit Tests I feel like they were not the most appropriate choice for this kind of service. Integration or functional tests would be a better fit since they would allow me to properly test each endpoint and even how they interact (e.g. create post and then get list of posts). I ended up not implementing these kinds of tests due to lack of time and knowledge but it would be the first thing I would improve.

## Execution

### Dependencies

Dependencies can be installed by running `yarn install` or `npm install`. 

### Run

The project can be run using Docker with `docker-compose up`. This will start two containers: a Node.js container which runs the application in debug mode and a PostgreSQL container.

When the containers are ready the API can be accessed at `http://localhost:8080/`.

### Debug

In order to debug the application the main container exposes debug port `5399`. I've provided a config for Visual Studio Code to attach to the running container in debug mode.

### Test

The project uses `jest` for unit tests. In order to execute the tests simply run `yarn test`, `npm run test` or even `npx jest`. In addition you can also run the `test:coverage` script to get code coverage report.
