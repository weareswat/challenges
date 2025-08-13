# List Posts by Rating Challenge 

![Ruby](https://img.shields.io/badge/ruby-%23CC342D.svg?style=for-the-badge&logo=ruby&logoColor=white)
![Rails](https://img.shields.io/badge/rails-%23CC0000.svg?style=for-the-badge&logo=ruby-on-rails&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

A Rails application that implements a Reddit-style post scoring system with logarithmic ranking algorithms and time decay factors.

## Technology Stack

- **Ruby**: 3.4.5
- **Rails**: 8.0.2
- **PostgreSQL**: Latest (Docker)
- **Docker**: Containerized development environment (Initial docker from https://github.com/muromeo1/rails-docker-boilerplate)

## Challenge

### List Posts by Rating

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

Tech stack: please use preferably Ruby / Rails or JAVA, but if you are more versatile in a different tech stack, you may use one of your choice.


## Features

- **Post Scoring System**: Implements a logarithmic scoring algorithm similar to Reddit's ranking system
- **Vote Management**: Supports upvotes and downvotes with proper score calculations

## Scoring Algorithm
I decided to use Logarithmic Score (Reddit-style) because: 
- Balances popularity and recency (old content naturally decays in ranking even if it has many votes)
- New content with even moderate upvotes can rise quickly
- Simple and fast to compute
- No confidence intervals or priors (just basic math and log)
- Directional control with sign
- Separates positive vs. negative reactions (posts with more downvotes than upvotes sink faster)
- Encourages early engagement (the time boost helps early upvoting significantly, rewarding viral momentum)

## Getting Started

### Prerequisites

- Docker
- Docker Compose

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd list_posts_by_rating_challenge
```

2. Start the application:
```bash
docker compose build
docker compose up
```

4. Access the application at `http://localhost:3000`

## Usage

### Running Commands

Use the `bin/run` script to execute commands within the Docker container:

```bash
# Rails commands
bin/run rails console
bin/run rails routes
bin/run rails db:migrate
bin/run rails db:seed

# Ruby commands
bin/run ruby -v
bin/run bundle install
```

### API Endpoints

- `GET /posts` - List all posts with scores
- `POST /posts/:id/upvote` - Upvote a post
- `POST /posts/:id/downvote` - Downvote a post

### Interface
Access the application at `http://localhost:3000` and interact with the visual interface, it will show you the upvote and downvote endpoints working plus the score system working.

## Development

### Project Structure

```
source/
├── app/
│   ├── controllers/
│   │   ├── application_controller.rb
│   │   ├── posts_controller.rb                    # Web interface controller
│   │   └── api/
│   │       └── v1/
│   │           └── posts_controller.rb            # API endpoints for posts
│   ├── models/
│   │   ├── application_record.rb
│   │   └── post.rb                               # Post model with scoring logic
│   ├── services/
│   │   └── post_scoring_service.rb               # Core scoring algorithm
│   ├── views/
│   │   ├── layouts/
│   │   │   └── application.html.erb
│   │   └── posts/
│   │       ├── index.html.erb                    # Main posts listing page
│   │       └── _table.html.erb                   # Posts table partial
│   └── assets/
│       ├── stylesheets/
│       └── javascript/
├── config/
├── db/
│   ├── migrate/
│   │   └── 20250811120000_create_posts.rb       # Posts table migration

│   └── seeds.rb                                  # Sample data
├── spec/
│   ├── models/
│   │   └── post_spec.rb                          # Post model tests
│   ├── controllers/
│   │   ├── posts_controller_spec.rb              # Web controller tests
│   │   └── api/
│   │       └── v1/
│   │           └── posts_controller_spec.rb      # API controller tests
│   ├── services/
│   │   └── post_scoring_service_spec.rb          # Scoring service tests
│   ├── requests/
│   │   └── api/
│   │       └── v1/
│   │           └── posts_spec.rb                 # API request tests
│   ├── factories/
│   │   └── posts.rb                              # Test data factories
│   └── features/
│       └── posts_spec.rb                         # Integration tests

```

### Key Files

- `app/services/post_scoring_service.rb` - Main scoring algorithm implementation
- `app/models/post.rb` - Post model with vote tracking
- `app/controllers/api/v1/posts_controller.rb` - API endpoints for posts

### Testing

Run the test suite:

```bash
bin/run rspec
```

## Configuration

### Time Decay Factor

The time decay factor is configurable in `PostScoringService`:

```ruby
TIME_DECAY_FACTOR = 45_000  # 12.5 hours in seconds, used by reddit
```

### Database

The application uses PostgreSQL for data persistence and efficient scoring queries.

## Notes
- The scoring system was translated to ruby with the help of AI
- The tests where created with the help of AI