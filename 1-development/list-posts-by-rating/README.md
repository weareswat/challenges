# list-posts-by-rating Submission

My submition for the requested InvoicExpress Challenge.

## Tech Stack

This solution uses Postgresql for its database.

The application is programed in JAVA, with the Spring Boot framework.

## Usage
Being in the /list-posts-by-rating/ directory:
```bash
psql -U postgres --file listpostsbyrating_db.sql
```
- this creates the database and the "posts" table. It also populates the database with a few posts.


```bash
mvn clean install
```
- this builds Spring Boot Project with Maven (clean command just to be sure)

```bash
mvn spring-boot:run
```
- runs the Spring Boot app using Maven

 
Now the server is running. Go to localhost on a web browser and make the requests to the API.
```bash
http://localhost:8080/posts
```
- this returns a response with all the posts currently present in the database, ordered by upvote ratio descending, downvote ratio ascending and total votes descending. 

```bash
http://localhost:8080/upvote/:{post_id}
```
- this increments the number of upvotes by 1 on the post present in the database with {post_id}

```bash
http://localhost:8080/downvote/:{post_id}
```
- this increments the number of downvotes by 1 on the post present in the database with {post_id}

### Extra

I also programmed an extra POST Request to add a post, for debugging purposes.
```bash
http://localhost:8080/addpost
```

 * this adds a new post to the database
      * The Request body needs to have a JSON object defined as follows, for example:

```json
{
    "upvotes" : 0,
    "downvotes" : 0,
    "postBody" : "This is the posts body 1."
}
```


## Database

The Database has only one table with the following rows:
- post_id (Primary Key);
- upvotes;
- downvotes;
- post_body;
- total_votes;

The post_id auto increments with a SQL Sequence. Every time an upvote or a downvote is incremented, the total_votes value is updated with the sum of upvotes with downvotes (total_votes = upvotes + downvotes).

## Debugging

I used Postman to debug the requests of this simple API.