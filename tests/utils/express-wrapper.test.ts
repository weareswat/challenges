import * as express from 'express';
import { ExpressWrapper } from '../../src/utils/express-wrapper';

// express mock
const expressMock = {
	use: jest.fn(),
	listen: jest.fn(),
};

jest.mock('express', () => {
	return () => {
		return expressMock;
	};
});

// body-parser mock
const bodyParserMock = {
	json: jest.fn(),
	urlencoded: jest.fn(),
};

jest.doMock('body-parser', () => {
	return bodyParserMock;
});

describe('ExpressWrapper', () => {
	afterAll(() => {
		jest.resetAllMocks();
	});

	it('should create instance of ExpressWrapper', () => {
		const router = {} as express.Router;

		new ExpressWrapper(3000, router);

		expect(expressMock.use).toHaveBeenLastCalledWith(router);
		expect(expressMock.use).toHaveBeenCalledTimes(3);
	});

	it('should create instance of ExpressWrapper and start listening', () => {
		const router = {} as express.Router;

		const wrapper = new ExpressWrapper(3000, router);
		wrapper.start();

		expect(expressMock.listen).toHaveBeenCalledWith(3000, expect.any(Function));
	});
});
