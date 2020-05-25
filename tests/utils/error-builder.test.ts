import { buildServiceError } from '../../src/utils/error-builder';
import { Errors } from '../../src/errors';

describe("buildServiceError()", () => {
    it("should return a ServiceError", () => {
        const error = buildServiceError(Errors.DatabaseError, 400);

        expect(error).toEqual({
            code: 400,
            error: Errors.DatabaseError,
            type: 'ServiceError',
        });
    });

    it("should return a ServiceError with inner error", () => {
        const innerError = new Error('test inner error');

        const error = buildServiceError(Errors.DatabaseError, 400, innerError);

        expect(error).toEqual({
            code: 400,
            error: Errors.DatabaseError,
            innerError,
            type: 'ServiceError',
        });
    });
});