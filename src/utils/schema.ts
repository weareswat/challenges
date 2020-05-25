import * as Joi from '@hapi/joi';
import { ContainerTypes, ValidatedRequestSchema } from 'express-joi-validation';
import { USER_HEADER } from '../constants';

export enum SchemaFields {
	Query = 'query',
	Params = 'params',
	Body = 'body',
	Headers = 'headers',
}

export type SchemaMap = {
	[key in SchemaFields]?: Joi.Schema;
};

export const headersSchema = Joi.object({
	[USER_HEADER]: Joi.string().required(),
});

export interface BaseRequestSchema extends ValidatedRequestSchema {
	[ContainerTypes.Headers]: {
		[USER_HEADER]: string;
	};
}
