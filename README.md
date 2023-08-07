# User Changes Challenge

## Prerequisites

The setups steps expect following tools installed on the system.

- PostgreSQL 14
- Ruby 3.2.2
- Rails 7.0.6

## Installation

#### 1. Clone the repository

```bash
git clone https://github.com/claudioduarte/user-changes-challenge.git
```

or, if you prefer, via SSH (recommended)

```bash
git clone git@github.com:claudioduarte/user-changes-challenge.git
```

#### 2. `cd` into the project

```ruby
cd user-changes-challenge
```

#### 3. Install Homebrew

```ruby
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
````

If you run into problems after the installation, please run the following commands:

```ruby
(echo; echo 'eval "$(/opt/homebrew/bin/brew shellenv)"') >> /Users/your_user/.zprofile
```
```ruby
eval "$(/opt/homebrew/bin/brew shellenv)"
```

#### 4. Install Ruby using `rbenv`

```ruby
brew install rbenv
```

Add the following command to your ~/.zshrc file to make rbenv load automatically when you open up the Terminal.

```ruby
eval "$(rbenv init -)" >> ~/.zshrc
```

Install Ruby 3.2.2 with the following command:

```ruby
rbenv install 3.2.2
```

Set the ruby version in the project, rehash and then restart the terminal to apply the changes

```ruby
rbenv local 3.2.2
rbenv rehash
```

When typing `ruby -v`, this should be the version:

```ruby
ruby 3.2.2 (2023-03-30 revision e51014f9c0) [arm64-darwin21]
```

#### 5. Install PostgreSQL with Homebrew

```ruby
brew install postgresql@14
```

#### 6. Install Bundler gem

```ruby
gem install bundler -v '2.4.10'
```

#### 7. Install all of your gem dependencies

```ruby
bundle install
```

If an error occurs, run command with `sudo` permissions
```ruby
sudo bundle install
```

#### 8. Start PostgreSQL in your local machine if not yet running

```ruby
brew services start postgresql@14
```

#### 9. Create and setup the database

```ruby
bundle exec rake db:create
```

#### 10. Start the Rails server in development mode

```ruby
rails s
```

#### 11. View the project

You can visit the website using the following URL: [http://localhost:3000](http://localhost:3000)

#### 12. Testing Endpoints

Two endpoints can be called:
````
POST /audits
GET /audits
````

##### GET /audits
This endpoint returns all audits that have been created so far.

`GET http://localhost:3000/audits/ID?field=FIELD_NAME&start_date=START_DATE&end_date=END_DATE`

Parameters included in the query string:
````
Parameter  | Value
-----------|-----
:id        | 1
field      | name or address.street
start_date | YYYY-MM-DD format (YYYY = year, MM = month, DD = day of the month)
end_date   | YYYY-MM-DD format (YYYY = year, MM = month, DD = day of the month)
````

Sample response:
```json
[
    {
        "field": "name",
        "old": "Bruce Norries",
        "new": "Bruce Whatsyourname"
    }
]
````


##### POST /audits
This endpoint creates an audit for a model change.

`POST http://localhost:3000/audits`

Payload for the POST request:
```json
{
  "audit": {
      "model": "User",
      "old": {
          "_id": 1,
          "name": "Bruce Norries",
          "address": {
              "street": "Some street"
          }
      },
      "new": {
          "_id": 1,
          "name": "Bruce Willis",
          "address": {
              "street": "Nakatomi Plaza"
          }
      }
  }
}
````

Response:
```json
{
    "status": 200,
    "success": "Audit created!"
}
````

#### 13. Run RSpec Tests

```ruby
rspec -fd
```

## License & Copyright

Licensed under the [MIT License](LICENSE). ©2023 Cláudio Duarte
