export type Post = {
	id: string;
	title: string;
	user: string;
	createdAt: Date;
	votes: number;
	upvotePercentage: number;
	downvotePercentage: number;
};
