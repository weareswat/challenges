# README

* Ruby version 3.1.3

* Database setup
   * Run migrations: `rails db:migrate`
	* Seed db: `rails db:seed` to seed posts defined in `db/seeds.rb`

* How to run the test suite
    :`rspec spec/`
    * Should also produce a coverage report, creating a `coverage/` directory. You can see the report by accessing `coverage/index.html` in your browser.


One way to run and test it:

- `rails s`
- if you ran `db:seed`you should have a couple of posts to work with
- Then, with the rails server running: `curl -XGET http://127.0.0.1:3000/posts`
	- Protip: get `jsonpp`so you can get a pretty json response with `curl -XGET http://127.0.0.1:3000/posts | jsonpp`
- To upvote/downvote `curl -XPUT http://127.0.0.1:3000/(upvote or downvote)/:post_id` e.x: `curl -XPUT http://127.0.0.1:3000/upvote/5`