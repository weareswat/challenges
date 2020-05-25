import { Errors } from "../errors";

export type ServiceError = {
    code: number;
    error: Errors,
    innerError?: Error; 
    type: 'ServiceError';
};

export function buildServiceError(error: Errors, code: number, innerError?: Error): ServiceError {
    return {
        code,
        error,
        innerError,
        type: 'ServiceError',
    };
}