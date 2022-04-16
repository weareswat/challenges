# List Posts by Rating

### Requirements:
> Visual Studio Community 2019 with the following workloads:
- .NET desktop development
- ASP.NET and web development
- Data storage and processing
- .NET Core cross-platform development

### Techonology used
> C# 9.0 (.NET 5)
> Linq
> Entity Framework Core 5.0.16
> Entity Framework Core inMemory 5.0.16 (In-memory database)
> Swashbuckle.AspNetCore 5.6.3
> OpenApi (with SwaggerUI)

# Table of contents
1. [Usage](##Usage)
	1. [Basic Testing](#basictesting)
2. [Code Tour - Models/](#codetour_models)
3. [Code Tour - Models/ExtensionMethods](#codetour_extensionmethods)
4. [Code Tour - Controllers/](#codetour_controllers)
---
## Usage
#### <a name="basictesting"></a> Basic Testing  

1. In the project folder, open the **ListPostsByRating.sln** file;
2. Once Visual Studio 2019 opens up, on the top bar click on **ISS Express** to run the application;
3. A web page in your default browser will appear, with the URL **http://localhost:8000/swagger/index.html**. This is the SwaggerUI, simple click on the endpoint > "Try it out" button > Execute;
4. If a tool like Postman is preferred, simply let the application run and copy and paste the following requests:
	* GET - http://localhost:8000/posts
	* PUT - http://localhost:8000/upvote/{post_id}
	* PUT - http://localhost:8000/downvote/{post_id}

**Note:** Every {post_id} should be >0 and within the total amount of posts, which by default, is 11.

#### <a name="codetour_models"></a> Code Tour - Models/
1. The file **BlogPost.cs**, is comprised of the following properties:
* **Id**: Unique identifier of each post, used in upvoting/downvoting requests;
* **Upvotes**: Plays a big part of determining the overall rating of a post;
* **Downvotes**: When coupled with Upvotes, it is used to determine a post's Ratio;
* **Ratio**: It is calculated by the difference of Upvotes and Downvotes;
* **UpvotePercentage**: Calculated by how many Upvotes a post has according to its total.

Every property is an integer, even the percentage as when it is calculated, trailling decimal numbers are ignored for simplicity sake.

The overall rating of posts is determined by their upvote percentage, followed by their ratio and finally by their upvotes.

A post with the most upvotes will not rank higher than one with better ratio ```(Upvote - Downvote)```
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

and a post with less upvotes but higher upvote percentage will rank higher than a post with more upvotes and lower upvote percentage if both have the same ratio:

```json
[
  {
    "id": 2,
    "upvotes": 6,
    "downvotes": 4,
    "ratio": 2,
    "upvotePercentage": 60
  },
  {
    "id": 8,
    "upvotes": 7,
    "downvotes": 5,
    "ratio": 2,
    "upvotePercentage": 58
  }
]
```

In the case they both have the same ratio and upvote percentage, the one with the most upvotes will rank higher:
```json
[
  {
    "id": 5,
    "upvotes": 800,
    "downvotes": 800,
    "ratio": 0,
    "upvotePercentage": 50
  },
  {
    "id": 6,
    "upvotes": 8,
    "downvotes": 8,
    "ratio": 0,
    "upvotePercentage": 50
  }
]
```
2. The file **ListPostsByRatingContext.cs**, is where the Entity Framework's database context is located, defining all the types of data present in our models.
It is also where entity/table's relations are made, for this project, however, relations were not necessary.
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
*Don't worry about the __CalculateUpvotePercentage()__ and __CalculateVoteRatio()__ extension methods, they are here to calculate their respective values with the hard coded Upvotes and Downvotes numbers so coders don't have to waste time calculating them themselves, they are also explained in the next sub chapter.*

The hard coded data here is for testing purposes, their values, altered or not during testing, will remain in memory until the program is shutdown, in which, upon restart values will be reset to what they were previously. The added posts will automatically be rated in the **BlogPostController.cs**, also explained later.

#### <a name="codetour_extensionmethods"></a> Code Tour - Models/ExtensionMethods
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

#### <a name="codetour_controllers"></a> Code Tour - Controllers/
4. In the file **BlogPostController.cs**, is where access to the state of the BlogPost model and response methods are made. Methods in this controller are called Actions that can return various status codes as well as data from the database. Every Action runs asynchronously.
4.1 In the following constructor, we receive the database context and inject it onto a local property for later use, such as querying. As precaution, it is checked whether or not the database context is null and, since the database is "In Memory" we also check if it is initialized via ```.Database.EnsureCreated()```.

```cs
private readonly ListPostsByRatingContext blogPostContext;

public BlogPostController(ListPostsByRatingContext blogPostContext)
{
	if (blogPostContext == null)
		throw new ArgumentNullException();
		
	this.blogPostContext = blogPostContext;
	this.blogPostContext.Database.EnsureCreated();
}
```

4.2 Each Action is routed with attributes containing their respective HTTP Verbs, let's start with the most basic one, ```[HttpGet]```
 
```cs
[HttpGet("posts")]
public async Task<IActionResult> FetchAllBlogPosts()
{
	return Ok(await blogPostContext.BlogPost.OrderByDescending(i => i.Ratio)
						.ThenByDescending(i => i.Upvotes)
						.ThenByDescending(i => i.UpvotePercentage)
						.ToListAsync());
}
```
This URI lists every BlogPost descendingly, so the top rated posts stay on top, when a GET request is made to /posts. Ratio is very important for post rating, but if two posts have this same value then whichever has the most upvote percentage gets ranked higher.

4.3 The next two Actions, **UpvotePost(...)** and **DownvotePost(...)** work similarly, so only the former will be explained, the difference between them is in which Post property is incremented.

* It is first checked whether or not the post Id is valid, it has to be a positive integer, returning a "409 Conflict" status code otherwise;
* Then a database query is made to see if a post with the given Id exists, if not, it returns a null object, in which case, returns a "404 Not Found" status code to the client;
* If a post based on the given Id exists, the upvote value (or downvote value) is incremented, upvote percentage and vote ratio calculated. With the modified state being saved to the database. Returning the post with the updated values.
```cs
[HttpPut("upvote/{post_id:int}")]
public async Task<IActionResult> UpvotePost(int post_id)
{
	if (post_id <= 0)
	    return Conflict();

	BlogPost blogPost = blogPostContext.BlogPost.Where(b => b.Id == post_id).FirstOrDefault();

	if (blogPost == null)
	    return NotFound();

	try
	{
	    blogPost.Upvotes++;
	    blogPost.CalculateUpvotePercentage().CalculateVoteRatio();

	    blogPostContext.Entry(blogPost).State = EntityState.Modified;

	    await blogPostContext.SaveChangesAsync();
	}
	catch(ArgumentNullException)
	{
	    return NotFound();
	}
	catch (DbUpdateConcurrencyException)
	{
	    throw;
	}
	
	return Ok(blogPost);
}
```
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
