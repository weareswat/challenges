import { RouterBuilder } from '../utils/router-builder';
import { getAll, createPostSchema, create, getOnePostSchema, getOne } from './posts';
import { votesSchema, upvote, downvote } from './votes';

const builder = new RouterBuilder();

builder.get('/posts', getAll);

builder.get('/posts/:post_id', getOne, getOnePostSchema);

builder.post('/posts', create, createPostSchema);

builder.put('/posts/:post_id/upvote', upvote, votesSchema);
builder.put('/upvote/:post_id', upvote, votesSchema);

builder.put('/posts/:post_id/downvote', downvote, votesSchema);
builder.put('/downvote/:post_id', downvote, votesSchema);

export const router = builder.router;
