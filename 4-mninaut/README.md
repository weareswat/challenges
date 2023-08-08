## Installation

#### 1. Clone the repository

```bash
git clone https://github.com/mninaut/challenges.git
```

### 2. Run the follow commands

```bash
rails db:create
rails db:migrate
rails db:seed #to populate the database with description content
```

### 3. Test Endpoints

```bash
GET /posts
```
```bash
[
	{
		"id": "2f239e3e-b8ab-4d79-8eec-76fc8de2c06c",
		"title": "Post Title",
		"rating": "60.0%",
		"up_votes": 6,
		"down_votes": 4
	},
	{
		"id": "53fe55cd-46c8-48da-bf18-f4ba66482d90",
		"title": "Post Title 2",
		"rating": "60.0%",
		"up_votes": 600,
		"down_votes": 400
	},
	{
		"id": "6a81be38-076e-4687-949a-a5d5bf61132e",
		"title": "Post Title 3",
		"rating": "71.0%",
		"up_votes": 71,
		"down_votes": 29
	}
]
```
#### On challenge description is said is a user can vote so to make the vote  request its necessary past USER_ID on header with you run the seeds commands you can use this user_id for test "6003930e-1c6a-46d6-a4b7-afb1d8e8fe37"

```bash

PUT /upvote/post_id
PATCH  /upvote/post_id
PUT /downvote/post_id
PATCH  /downvote/post_id

HEADER USER_ID
```
```bash
{
	"kind": "up",
	"id": "50d5659e-44cf-445c-bbd5-bcdef6dcd683",
	"user_id": "6003930e-1c6a-46d6-a4b7-afb1d8e8fe37",
	"post_id": "5c30d6aa-5750-4d86-931b-52bf7b6e312c",
	"created_at": "2023-08-08T04:18:16.076Z",
	"updated_at": "2023-08-08T04:18:56.490Z"
}
```

