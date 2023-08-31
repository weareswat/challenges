# List Posts by Rating

This project is a response to the [List Posts by Rating](https://github.com/paultergust/posts-by-rating/blob/master/1-development/list_posts_by_rating.md) challenge. 
The idea is to have an API which stores users, who create posts, which are ranked according to a "upvotes"/"total votes" ratio, with an upvotes count tie breaker.

## Dependencies

* Ruby On Rails
* PostgreSQL or SQLite
* Docker
* Docker Compose

## How to run

### If you want to run it in a Docker container

This will spin up two containers. One with a PostgreSQL db, and one with the API itself.

``` shell
sudo docker-compose up
```

### If you want to run it locally

You can either have PostgreSQL running on your machine and change the `host` variable in `config/database.yml`:
```yml
default: &default
  adapter: postgresql
  encoding: unicode
  host: localhost # <<- this one
  username: postgres
  password: password
  pool: 5

development:
  <<: *default
  database: myapp_development


test:
  <<: *default
  adapter: sqlite3
  database: db/test.sqlite3

local:
  <<: *default
  adapter: sqlite3
  database: db/local.sqlite3

```

Or you can set this environment variable: 

``` shell
export RAILS_ENV=local
```

Then, for convenience, the database is gonna be a SQLite instance stored in `db/local.sqlite3`

And you can run the project with:

``` shell
rails server
```

## Running tests

We have unit test coverage for Users and Posts, both on their models and controllers. To run all tests just run

``` shell
rails test
```

## Disclaimer

This project does *not* emcompass security (either on the perpective of a user or an API), nor best practices for production-level software. This is very much a proof-of-concept just for the sake of a coding challenge.
