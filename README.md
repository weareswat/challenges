# List_posts_by_rating

### Requirements:
> Visual Studio Community 2019 with the following workloads:
- .NET desktop development
- ASP.NET and web development
- Data storage and processing
- .NET Core cross-platform development

### Techonology used
> C# 9.0 (.NET 5)
> Entity Framework Core 5.0.16
> Entity Framework Core inMemory 5.0.16 (In-memory database)
> Swashbuckle.AspNetCore 5.6.3
> OpenApi (with SwaggerUI)
## Usage
***
#### Basic Testing
1. In the project folder, open the **ListPostsByRating.sln** file;
2. Once Visual Studio 2019 opens up, on the top bar click on **ISS Express** to run the application;
3. A web page in your default browser will appear, with the URL **http://localhost:8000/swagger/index.html**. This is the SwaggerUI, simple click on the endpoint > "Try it out" button > Execute;
3.1 If a tool like Postman is preferred, simply let the application run and copy and paste the following requests:
3.1.1 GET - http://localhost:8000/posts
3.1.2 PUT - http://localhost:8000/upvote/{post_id}
3.1.3 PUT - http://localhost:8000/downvote/{post_id}
Note: Every {post_id} should be >0 and within the total amount of posts, which by default, is 8.

#### Code Tour - Models/
1. The file **BlogPost.cs**, is comprised of the following properties:
1.1 - **Id**: Unique identifier of each post, used in upvoting/downvoting requests;
1.2 - **Upvotes**: Plays a big part of determining the overall rating of a post;
1.3 - **Downvotes**: When coupled with Upvotes, it is used to determine a post's Ratio;
1.4 - **Ratio**: It is calculated by the difference of Upvotes and Downvotes;
1.5 - **UpvotePercentage**: Calculated by how many Upvotes a post has according to its total.
Every property is an integer, even the percentage as when it is calculated, trailling decimal numbers are ignored for simplicity sake.

The overall rating of posts are determined by their upvote percentage, followed by their ratio and finally by their upvotes.

A post with the most upvotes might not rank higher than one with better ratio ```(Upvote - Downvote)```
```json
[
  {
    "id": 1,
    "upvotes": 600,
    "downvotes": 400,
    "ratio": 200,
    "upvotePercentage": 60
  },
  {
    "id": 7,
    "upvotes": 967,
    "downvotes": 800,
    "ratio": 167,
    "upvotePercentage": 54
  }
]
```

and a post with the most upvote percentage won't rank higher than a post with more upvotes, if both have the same ratio

```json
[
	  {
    "id": 8,
    "upvotes": 7,
    "downvotes": 5,
    "ratio": 2,
    "upvotePercentage": 58
  },
  {
    "id": 2,
    "upvotes": 6,
    "downvotes": 4,
    "ratio": 2,
    "upvotePercentage": 60
  }
]
```

2. The file **ListPostsByRatingContext.cs**, is where the Entity Framework's database context is located, defining all the types of data present in our models.
It is also where entitiy/table's relations are made, for this project those are not necessary.
Data is also inserted in the BlogPost Entity ```DbSet<BlogPost> BlogPost```, under the *OnModelCreating(...)* method with the following code:
```cs
modelBuilder.Entity<BlogPost>().HasData(
	new BlogPost
	{
		Id = 1,
		Upvotes = 600,
		Downvotes = 400,
		Ratio = 0,
		UpvotePercentage = 0
	}.CalculateUpvotePercentage().CalculateVoteRatio(),
	// Code omitted
```
*Don't worry about the __CalculateUpvotePercentage()__ and __CalculateVoteRatio()__ extension methods, they are here to calculate their respective values with the hard coded Upvotes and Downvotes numbers so coders don't have to waste time calculating them themselves, they are also explained in the next sub chapter*

The hard coded data here is for testing purposes, their values, altered or not during testing, will remain in memory until the program is shutdown. The added posts will automatically be rated in the **BlogPostController.cs**, also explained later

#### Code Tour - Models/ExtensionMethods
3. These [extension methods](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/extension-methods#common-usage-patterns) only work with BlogPost objects (present in the Models/ folder), hence why they were attached to each newly created BlogPost hard coded data:

```cs
modelBuilder.Entity<BlogPost>().HasData(
	new BlogPost {...}.CalculateUpvotePercentage().CalculateVoteRatio());
```

3.1 The first extension method, *CalculateUpvotePercentage(...)*, calculates the upvote percentage without worrying about the C# operator precedence:
```cs
public static BlogPost CalculateUpvotePercentage(this BlogPost blogPost)
{
	double totalVotes = blogPost.Upvotes + blogPost.Downvotes;
	double upvotePercentage = blogPost.Upvotes / totalVotes;
	upvotePercentage *= 100;

	// Protection against zero divided by zero
	if (Double.IsNaN(upvotePercentage))
		upvotePercentage = 0;

	blogPost.UpvotePercentage = (int)upvotePercentage;
	return blogPost;
}
```

3.2 It returns itself with the updated data, *CalculateVoteRatio(...)* does this as well:
```cs
public static BlogPost CalculateVoteRatio(this BlogPost blogPost)
{
	blogPost.Ratio = blogPost.Upvotes - blogPost.Downvotes;
	return blogPost;
}
```

#### Code Tour - Controllers/
4. 

***
# Challenges
Code challenges for developers and designers.

We believe recruitment is the most critical part of an organization. People should experience some time working together before deciding on making it permanent.
This repo contains code challenges we'd like to see solved by people interested in working with us.

We tend not to place job ads, as we prefer references and proactive candidates. With this in mind, feel free to take a challenge and let us know you're working on it.
Mostly, we work with Ruby and Clojure, but also have some stuff in Node.js. Even if you don't have experience in these technologies, as long as you are willing to learn them and want to build great stuff for the web, we can probably be a good fit.


### Available challenges

#### Backend Development
* [User Changes](/1-development/user-changes.md)
* [Client from the Bahamas](/1-development/client-from-the-bahamas.md)
* [List Posts by Ratings](/1-development/list_posts_by_rating.md)

#### Frontend Development
* [React Challenge](/2-frontend/react-challenge.md)
* [Design Implementation Challenge](/3-design-frontend/design-frontend-challenge.md)


### How we work ##

* **People** - you know, the guys in the team(s). We're not afraid to ask for help and we're not afraid to express our opinions. We're here to help.eachother.

* **Culture** - We follow RUPEAL's guiding principles:

  * Give your best
  * Show That You Care
  * Build an environment of strong, open and honest relationships
  * Deliver WOW through your service
  * Stay humble
  * Do What's Right
  * Be coachable and don’t take it personally
  * Do more with less
  * Pursue growth and personal development
  * Have fun

* **You** - by joining our team, feel free to question all the items below and propose new ideas on how we work. This is definitely not a static thing.

* **GitHub** - all our code is hosted here. We use Pull Requests and do code reviews for those. Everyone on the team reviews PRs. It's expected that you write quality code with automated tests. Once a PR is reviewed and accepted, the person who opened the PR should merge it and delete the branch.

* **Continuous Integration** - we use Semaphore for our CI needs. Everytime we push to a branch, our test suite runs on Semaphore.

* **Slack** - we mostly communicate asynchronously between the development team and with other teams. This is our chat tool. Abuse it.

* **Pivotal Tracker** - our products' backlog is managed in Pivotal Tracker. Once we estimate all known user-stories, the sprint backlog is automatically built. Each team member will select a user-story to work from that sprint backlog and will update the needed tasks' status. This way we keep focused until the end of the sprint.

* **Support tasks** - We are paranoid about providing top notch support to our customers. We have a dedicated customer support team working full-time communicating with clients. Our development team works very closely with the customer support team delivering happiness to our clients.
