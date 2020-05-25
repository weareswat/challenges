import * as Joi from '@hapi/joi';
import { ContainerTypes, ValidatedRequest } from 'express-joi-validation';
import { VOTES_TABLE, POSTS_TABLE, USER_HEADER } from '../constants';
import * as pgsql from '../utils/pgsql';
import { Post } from '../entities/post';
import { BaseRequestSchema } from '../utils/schema';

export const votesSchema = {
	params: Joi.object({
		post_id: Joi.string().required(),
	}),
};

export interface VoteRequestSchema extends BaseRequestSchema {
	[ContainerTypes.Params]: {
		post_id: string;
	};
}

export async function upvote(req: ValidatedRequest<VoteRequestSchema>) {
	const vote = {
		post_id: req.params.post_id,
		user: req.headers[USER_HEADER],
		vote: 1,
	};

	await saveVote(vote);
}

export async function downvote(req: ValidatedRequest<VoteRequestSchema>) {
	const vote = {
		post_id: req.params.post_id,
		user: req.headers[USER_HEADER],
		vote: -1,
	};

	await saveVote(vote);
}

type Vote = {
	post_id: Post['id'];
	user: string;
	voted_at?: Date;
	vote: number; // 1 = upvote, -1 = downvote
};

async function saveVote(vote: Vote) {
	// save to votes table and update posts table within transaction
	// ideally this should be done in a stored procedure

	return pgsql.withinTransaction(async (transaction) => {
		await pgsql.tryCatch(transaction, async (conn) => {
			// using upsert to make sure user can only vote once per post
			await pgsql.upsert({
				conn,
				tableName: VOTES_TABLE,
				insertObject: vote,
				updateObject: {
					vote: vote.vote,
					voted_at: new Date(),
				},
				conflictColumns: ['post_id', 'user'], // primary keys
			});

			// calculate vote percentages and update posts table
			const result = await conn
				.table(VOTES_TABLE)
				.select([
					conn.raw('count(*) FILTER (WHERE vote = 1) AS upvotes'),
					conn.raw('count(*) AS total'),
				])
				.where('post_id', vote.post_id)
				.first();

			const votes = Number(result.total);
			const upvotePercentage = Number(result.upvotes) / votes;
			const downvotePercentage = 1 - upvotePercentage;

			await conn
				.table(POSTS_TABLE)
				.update({
					votes,
					upvote_percentage: upvotePercentage,
					downvote_percentage: downvotePercentage,
				})
				.where('id', vote.post_id);
		});
	});
}
