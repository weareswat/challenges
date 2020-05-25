import * as uuid from 'uuid';
import * as Joi from '@hapi/joi';
import { ContainerTypes, ValidatedRequest } from 'express-joi-validation';
import * as pgsql from '../utils/pgsql';
import { Post } from '../entities/post';
import { POSTS_TABLE, USER_HEADER } from '../constants';
import { BaseRequestSchema } from '../utils/schema';
import { buildServiceError } from '../utils/error-builder';
import { Errors } from '../errors';

export const createPostSchema = {
	body: Joi.object({
		title: Joi.string().required(),
	}),
};

export interface CreatePostRequestSchema extends BaseRequestSchema {
	[ContainerTypes.Body]: {
		title: string;
	};
}

export async function create(req: ValidatedRequest<CreatePostRequestSchema>) {
	const newPost = {
		id: uuid.v4(),
		user: req.headers[USER_HEADER],
		createdAt: new Date(),
		title: req.body.title,
		votes: 0,
		upvotePercentage: 0,
		downvotePercentage: 0,
	};

	await pgsql.tryCatch(undefined, async (conn) => {
		await conn.table(POSTS_TABLE).insert(transform(newPost));
	});

	return newPost;
}

export async function getAll() {
	const posts = await pgsql.tryCatch(undefined, async (conn) => {
		const query = conn
			.table(POSTS_TABLE)
			.select('*')
			.orderBy([
				{ column: 'upvote_percentage', order: 'desc' },
				{ column: 'downvote_percentage', order: 'asc' },
				{ column: 'votes', order: 'desc' },
				{ column: 'created_at', order: 'desc' },
			]);

		return (await query).map(reverseTransform);
	});

	return posts;
}

export const getOnePostSchema = {
	params: Joi.object({
		post_id: Joi.string().required(),
	}),
};

export interface GetOnePostRequestSchema extends BaseRequestSchema {
	[ContainerTypes.Params]: {
		post_id: string;
	};
}

export async function getOne(req: ValidatedRequest<CreatePostRequestSchema>) {
	const post = await pgsql.tryCatch(undefined, async (conn) => {
		const result = await conn
			.table(POSTS_TABLE)
			.select('*')
			.where('id', req.params.post_id)
			.first();

		if (result) {
			return reverseTransform(result);
		}

		return;
	});

	if (!post) {
		throw buildServiceError(Errors.UnknownPost, 400);
	}

	return post;
}

type PostRaw = {
	id: string;
	title: string;
	user: string;
	created_at: Date;
	votes: number;
	upvote_percentage: number;
	downvote_percentage: number;
};

function transform(post: Post): PostRaw {
	return {
		id: post.id,
		title: post.title,
		user: post.user,
		created_at: post.createdAt,
		votes: post.votes,
		upvote_percentage: post.upvotePercentage,
		downvote_percentage: post.downvotePercentage,
	};
}

function reverseTransform(raw: PostRaw): Post {
	return {
		id: raw.id,
		title: raw.title,
		user: raw.user,
		createdAt: new Date(raw.created_at),
		votes: raw.votes,
		upvotePercentage: raw.upvote_percentage,
		downvotePercentage: raw.downvote_percentage,
	};
}
