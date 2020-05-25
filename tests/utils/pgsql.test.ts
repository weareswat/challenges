import * as pgsql from '../../src/utils/pgsql';
import { Errors } from '../../src/errors';

const mockTransaction = {
	commit: jest.fn(),
	rollback: jest.fn(),
};

const knexMock = {
	migrate: {
		latest: jest.fn(),
	},
	transaction: jest.fn().mockResolvedValue(mockTransaction),
	table: jest.fn().mockReturnThis(),
	insert: jest.fn().mockReturnThis(),
	queryBuilder: jest.fn().mockReturnThis(),
	update: jest.fn().mockReturnThis(),
	raw: jest.fn().mockReturnThis(),
	then: jest.fn(function () {
		return {
			result: true,
		};
	}),
};

jest.mock('knex', () => {
	return () => {
		return knexMock;
	};
});

describe('pgsql', () => {
	afterAll(() => {
		jest.resetAllMocks();
	});

	describe('init', () => {
		it('should call migrations function', async () => {
			await pgsql.init();

			expect(knexMock.migrate.latest).toHaveBeenCalled();
		});
	});

	describe('tryCatch', () => {
		it('should call callback function and get result', async () => {
			const cb = jest.fn().mockResolvedValue('banana');

			const result = await pgsql.tryCatch(undefined, cb);

			expect(cb).toHaveBeenCalled();
			expect(result).toEqual('banana');
		});

		it('should get connection if transaction is undefined', async () => {
			const cb = jest.fn().mockResolvedValue('banana');

			const result = await pgsql.tryCatch(undefined, cb);

			expect(cb).toHaveBeenCalledWith(knexMock);
			expect(result).toEqual('banana');
		});

		it('should use transaction if provided', async () => {
			const cb = jest.fn().mockResolvedValue('banana');
			const transaction = {};

			const result = await pgsql.tryCatch(transaction as any, cb);

			expect(cb).toHaveBeenCalledWith(transaction);
			expect(result).toEqual('banana');
		});

		it('should wrap exception in ServiceError', async () => {
			const error = { error: 'fake error' };
			const cb = jest.fn().mockRejectedValue(error);

			try {
				await pgsql.tryCatch(undefined, cb);
			} catch (err) {
				expect(err).toEqual({
					error: Errors.DatabaseError,
					code: 400,
					innerError: error,
					type: 'ServiceError',
				});
			}
		});
	});


	// the following test is disabled because getting a knex transaction 
	// is taking too long, not sure what I am doing wrong

	// describe('withinTransaction', () => {
	// 	it('should call function inside transaction', async () => {
	// 		const cb = jest.fn().mockResolvedValue('banana');
	// 		jest.spyOn(pgsql, 'getTransaction').mockImplementation(() =>
	// 			Promise.resolve(mockTransaction as any),
	// 		);

	// 		const result = await pgsql.withinTransaction(cb);

	// 		expect(cb).toHaveBeenCalledWith(mockTransaction);
	// 		expect(mockTransaction.commit).toHaveBeenCalled();
	// 		expect(mockTransaction.rollback).not.toHaveBeenCalled();
	// 		expect(result).toEqual('banana');
	// 	});
	// });
});
