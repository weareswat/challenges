import * as controller from '../../src/routes/votes';
import * as pgsql from '../../src/utils/pgsql';
import { USER_HEADER } from '../../src/constants';
import { Errors } from '../../src/errors';

describe('votes', () => {
	afterAll(() => {
		jest.resetAllMocks();
	});

	describe('upvote', () => {
		const req = {
			headers: {
				[USER_HEADER]: 'test_user',
			},
			params: {
				post_id: '024eadf8-3e82-40f7-91f5-10df9af3ae42',
			},
		};

		it('should upvote existing post in DB', async () => {
			const tryCatchSpy = jest
				.spyOn(pgsql, 'withinTransaction')
				.mockImplementation(() => Promise.resolve());

			await controller.upvote(req as any);

			expect(tryCatchSpy).toHaveBeenCalled();
		});

		it('should throw exception if failed to access DB', async () => {
			const error = {
				code: 400,
				error: Errors.DatabaseError,
				type: 'ServiceError',
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockRejectedValue(error);

			try {
				await controller.upvote(req as any);
			} catch (err) {
				expect(tryCatchSpy).toHaveBeenCalled();
				expect(err).toEqual(error);
			}
		});
	});

	describe('downvote', () => {
		const req = {
			headers: {
				[USER_HEADER]: 'test_user',
			},
			params: {
				post_id: '024eadf8-3e82-40f7-91f5-10df9af3ae42',
			},
		};

		it('should downvote existing post in DB', async () => {
			const tryCatchSpy = jest
				.spyOn(pgsql, 'withinTransaction')
				.mockImplementation(() => Promise.resolve());

			await controller.downvote(req as any);

			expect(tryCatchSpy).toHaveBeenCalled();
		});

		it('should throw exception if failed to access DB', async () => {
			const error = {
				code: 400,
				error: Errors.DatabaseError,
				type: 'ServiceError',
			};

			const tryCatchSpy = jest.spyOn(pgsql, 'tryCatch').mockRejectedValue(error);

			try {
				await controller.downvote(req as any);
			} catch (err) {
				expect(tryCatchSpy).toHaveBeenCalled();
				expect(err).toEqual(error);
			}
		});
	});
});
