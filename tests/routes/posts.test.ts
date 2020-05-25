import * as controller from '../../src/routes/posts';
import * as pgsql from '../../src/utils/pgsql';
import { USER_HEADER } from '../../src/constants';
import { Errors } from '../../src/errors';

jest.mock('uuid', () => {
	return {
		v4: jest.fn().mockReturnValue('FAKE_UUID'),
	};
});

describe('posts', () => {
	afterAll(() => {
		jest.resetAllMocks();
	});

	describe('create', () => {
		const req = {
			headers: {
				[USER_HEADER]: 'test_user',
			},
			body: {
				title: 'fake title',
			},
		};

		it('should create new post and return it', async () => {
			const tryCatchSpy = jest
				.spyOn(pgsql, 'tryCatch')
				.mockImplementation(() => Promise.resolve());

			const post = await controller.create(req as any);

			expect(tryCatchSpy).toHaveBeenCalled();
			expect(post).toEqual({
				id: 'FAKE_UUID',
				user: req.headers[USER_HEADER],
				createdAt: expect.any(Date),
				title: req.body.title,
				votes: 0,
				upvotePercentage: 0,
				downvotePercentage: 0,
			});
		});

		it('should throw exception if unable to create post in DB', async () => {
			const error = {
				code: 400,
				error: Errors.DatabaseError,
				type: 'ServiceError',
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockRejectedValue(error);

			try {
				await controller.create(req as any);
			} catch (err) {
				expect(tryCatchSpy).toHaveBeenCalled();
				expect(err).toEqual(error);
			}
		});
	});

	describe('getAll', () => {
		it('should return a list of posts read from the DB', async () => {
			const dbPosts = [
				{
					id: '25c465b8-9ddf-44a3-b85b-beb8b2770c59',
					title: 'cenaita',
					user: 'aquele_user',
					createdAt: '2020-05-23T18:33:19.479Z',
					votes: 1,
					upvotePercentage: 1,
					downvotePercentage: 0,
				},
				{
					id: '024eadf8-3e82-40f7-91f5-10df9af3ae42',
					title: 'jgjghj',
					user: 'adasd',
					createdAt: '2020-05-23T17:38:52.090Z',
					votes: 4,
					upvotePercentage: 0.5,
					downvotePercentage: 0.5,
				},
				{
					id: '01d454d0-cfb4-47d8-aaf1-4f2f1e5844e5',
					title: 'qweqweqw',
					user: 'adasd',
					createdAt: '2020-05-23T17:38:44.868Z',
					votes: 2,
					upvotePercentage: 0.5,
					downvotePercentage: 0.5,
				},
				{
					id: 'c6cef386-42dd-4467-84fd-eb7654da39b8',
					title: 'cenaitaasdasd',
					user: 'adasd',
					createdAt: '2020-05-23T18:19:07.949Z',
					votes: 0,
					upvotePercentage: 0,
					downvotePercentage: 0,
				},
				{
					id: '6b2a18d8-9f8f-4eaa-9e01-aa0b5f455cb4',
					title: '1231',
					user: 'adasd',
					createdAt: '2020-05-23T17:38:47.360Z',
					votes: 1,
					upvotePercentage: 0,
					downvotePercentage: 1,
				},
			];

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockResolvedValue(dbPosts);

			const posts = await controller.getAll();

			expect(tryCatchSpy).toHaveBeenCalled();
			expect(posts).toEqual(dbPosts);
		});

		it('should throw exception if unable to read posts from DB', async () => {
			const error = {
				code: 400,
				error: Errors.DatabaseError,
				type: 'ServiceError',
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockRejectedValue(error);

			try {
				await controller.getAll();
			} catch (err) {
				expect(tryCatchSpy).toHaveBeenCalled();
				expect(err).toEqual(error);
			}
		});
	});

	describe('getOne', () => {
		const req = {
			params: {
				post_id: '024eadf8-3e82-40f7-91f5-10df9af3ae42',
			},
		};

		it('should return a posts read from the DB', async () => {
			const dbPost = {
				id: '024eadf8-3e82-40f7-91f5-10df9af3ae42',
				title: 'jgjghj',
				user: 'adasd',
				createdAt: '2020-05-23T17:38:52.090Z',
				votes: 4,
				upvotePercentage: 0.5,
				downvotePercentage: 0.5,
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockResolvedValue(dbPost);

			const post = await controller.getOne(req as any);

			expect(tryCatchSpy).toHaveBeenCalled();
			expect(post).toEqual(dbPost);
		});

		it('should throw exception if unable to read post from DB', async () => {
			const error = {
				code: 400,
				error: Errors.DatabaseError,
				type: 'ServiceError',
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockRejectedValue(error);

			try {
				await controller.getOne(req as any);
			} catch (err) {
				expect(tryCatchSpy).toHaveBeenCalled();
				expect(err).toEqual(error);
			}
		});

		it('should throw exception if unable to find specified post in DB', async () => {
			const error = {
				code: 400,
				error: Errors.UnknownPost,
				type: 'ServiceError',
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockResolvedValue(undefined);

			try {
				await controller.getOne(req as any);
			} catch (err) {
				expect(tryCatchSpy).toHaveBeenCalled();
				expect(err).toEqual(error);
			}
		});
	});
});
