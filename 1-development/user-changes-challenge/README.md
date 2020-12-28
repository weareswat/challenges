# User-Changes challenge

This challenge was proposed as a Software Engineering interview.

![App flow](https://www.linkpicture.com/q/Architecture.png)

Routind documentation can be found in [`routes.md`](https://github.com/gespcunha/challenges/blob/user-changes-challenge/1-development/user-changes-challenge/routes.md/)

## Requirements
- [NodeJS](https://nodejs.org/)
- [PostgreSQL](https://www.postgresql.org/)

## Building
- `cd user-changes-challenge`
- `npm install`

## Creating the database and its schema
- Go to `/scripts` folder
- Run `psql -f createDB` and `psql -f createSchema`

## Running the app
- `cd server`
- `node server`

## Running tests
- `npm test`
