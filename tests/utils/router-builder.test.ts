import * as Joi from '@hapi/joi';
import * as validator from 'express-joi-validation';
import { RouterBuilder } from '../../src/utils/router-builder';
import { headersSchema } from '../../src/utils/schema';

// express mock
const routerMock = {
	use: jest.fn(),
	get: jest.fn(),
	post: jest.fn(),
	put: jest.fn(),
	delete: jest.fn(),
};

jest.mock('express', () => {
	return {
		Router: () => routerMock,
	};
});

// validator mock
const mockParamsValidator = {
	paramsValidator: true,
};

const mockQueryValidator = {
	queryValidator: true,
};

const validatorMock = {
	body: jest.fn(),
	query: jest.fn().mockImplementation(() => mockQueryValidator),
	params: jest.fn().mockImplementation(() => mockParamsValidator),
	headers: jest.fn(),
	fields: jest.fn(),
	response: jest.fn(),
};

jest.spyOn(validator, 'createValidator').mockImplementation(() => validatorMock);

describe('RouterBuilder', () => {
	afterAll(() => {
		jest.resetAllMocks();
	});

	it('should create instance of RouterBuilder', () => {
		new RouterBuilder();

		expect(validator.createValidator).toHaveBeenCalled();
		expect(routerMock.use).toHaveBeenCalledTimes(2);
		expect(validatorMock.headers).toHaveBeenCalledWith(headersSchema);
	});

	it('should add GET route', () => {
		const builder = new RouterBuilder();

		const endpoint = '/example';

		builder.get(endpoint, () => {});

		expect(routerMock.get).toHaveBeenCalledWith(endpoint, expect.any(Function));
	});

	it('should add POST route', () => {
		const builder = new RouterBuilder();

		const endpoint = '/example';

		builder.post(endpoint, () => {});

		expect(routerMock.post).toHaveBeenCalledWith(endpoint, expect.any(Function));
	});

	it('should add PUT route', () => {
		const builder = new RouterBuilder();

		const endpoint = '/example';

		builder.put(endpoint, () => {});

		expect(routerMock.put).toHaveBeenCalledWith(endpoint, expect.any(Function));
	});

	it('should add DELETE route', () => {
		const builder = new RouterBuilder();

		const endpoint = '/example';

		builder.delete(endpoint, () => {});

		expect(routerMock.delete).toHaveBeenCalledWith(endpoint, expect.any(Function));
	});

	it('should add route with one validation', () => {
		const builder = new RouterBuilder();

		const endpoint = '/example/:post_id';
		const schema = {
			params: Joi.object({
				post_id: Joi.string().required(),
			}),
		};

		builder.get(endpoint, () => {}, schema);

		expect(validatorMock.params).toHaveBeenCalledWith(schema.params);
		expect(routerMock.get).toHaveBeenCalledWith(endpoint, mockParamsValidator, expect.any(Function));
	});

	it('should add route with multiple validations', () => {
		const builder = new RouterBuilder();

		const endpoint = '/example/:post_id';
		const schema = {
			params: Joi.object({
				post_id: Joi.string().required(),
			}),
			query: Joi.object({
				orderBy: Joi.string().optional(),
				limit: Joi.number().optional(),
			})
		};

		builder.get(endpoint, () => {}, schema);

		expect(validatorMock.params).toHaveBeenCalledWith(schema.params);
		expect(routerMock.get).toHaveBeenCalledWith(endpoint, mockParamsValidator, mockQueryValidator, expect.any(Function));
	});
});
