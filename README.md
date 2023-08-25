## About

This project is a back-end api post list
  - User crud
  - Post crud
  - Rating by number of votes

## Technologies/prerequisites

This project was developed with the following technologies:

* Ruby `3.1.2` - You can use [ASDF](https://asdf-vm.com/guide/getting-started.html)
* PostgreSQL 14
  * OSX - `$ brew install postgresql` or install [Postgress.app](http://postgresapp.com/)
  * Linux - `$ sudo apt-get install postgresql`
  * Windows - [PostgreSQL for Windows](http://www.postgresql.org/download/windows/)
* Bundler `2.3.7`

## Usage
After you get all the [prerequisites](#prerequisites), simply execute the following commands in sequence:

1. `gem install pg -v '1.3.4'`
2. `bundle`
3. `bundle exec rake db:create`
4. `bundle exec rake db:migrate`

You can run seed to populate the database with information from the posts and users api

- `bundle exec rake db:seed`

## INFO

A [documentation/collection](https://documenter.getpostman.com/view/17370497/2s9Y5WxPGC) was created in postman to help with endpoints
where basically the endpoints are:

### To run the tests:

- `bundle exec rspec`

# TODO

- Write documentation with Swagger
- add docker
- add authentications on API
- add authentication for users
- add pagination on Get post api


--- 
Thanks for the opportunity, this was made with by Gabriel Vieira :wave:&nbsp; [Get in touch!](https://www.linkedin.com/in/gevvieira/)
