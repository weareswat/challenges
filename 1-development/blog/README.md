README
======

Operating system used: Linux Mint 20 Ulyana
Ruby version used: 2.7.0p0
Rails version used: 6.1.1

This web service was created based on https://guides.rubyonrails.org/getting_started.html (v6.1.0) from sections 1 to 6. Section 7 adds a second model. To answer this challenge I would have to add a "voting" model, which I don't think exists the way I wanted it: simple and efficient.

Therefore, and also with the objective to learn how to add a voting mechanism without using "generate mode", I tried to add it manually.

I used the default SQLite database because it was sufficient for this purpose. It would not be the case for a production application.

Instructions to create the database:

$ cd blog
$ bin/rails db:migrate

Instructions to locally run this web service:

$ cd blog
$ rails server

Then point your browser to:

http://127.0.0.1:3000

The posts are listed by rating (highest rate first), then by number of votes (most voted first), and finally by creation date (most recent first).

I chose to implement rating as the difference between upvotes and downvotes. If I had chosen a ratio (upvotes/downvotes), I would have to deal with division by zero: what would it mean to have a post with 0 downvotes, leading to an infinite score?

The logic is that they are first rated by votes, then by audience: if a post has been voted many times it must be popular. And finally, in case of a tie, it's sorted by date: newest posts first.

To do
=====

Testing.

Testing
=======

We could script some calls to:

irb> article = Article.new(title: "...", body: "...")

Then several arbitrary (planned) calls to both:

irb> article.upvote
irb> article.downvote

and finally:

irb> article.save

We could then call:

irb> Article.all.order("rating desc, votes desc, created_at desc")

and compare it with the known-good list of topics. Results should match for the test to pass.

We should get away with differences in the creation and updation timestamps between tests if the posts are created in the same order. If this is not the case, a testing mechanism independent of time should be used.

Final words
===========

This work took me around 8 h plus some research on last Friday. It obviously looks very basic from an experienced person point-of-view, but for me it was a good step forward. I've only briefly looked at Ruby code, never used Rails before, and knew very little about web services. My past experience is in: algorithms for EDA (Electronic Design Automation), Embedded Software, Reliable Software and Entrepreneurship, that's why all this is mostly new to me.
