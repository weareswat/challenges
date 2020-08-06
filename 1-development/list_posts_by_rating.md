# List Posts by Rating

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
