import * as Joi from '@hapi/joi';
import * as express from 'express';
import { createValidator, ExpressJoiInstance } from 'express-joi-validation';
import { headersSchema, SchemaMap, SchemaFields } from './schema';
import { USER_HEADER } from '../constants';

/**
 * this builder is used to build an Express Router with the necessary configurations,
 * validations and response/error wrapping for simpler controller code
 */
export class RouterBuilder {
	router: express.Router;
	private validator: ExpressJoiInstance;

	constructor() {
		this.validator = createValidator();

		this.router = express.Router();

		// define headers schema requiring user
		this.router.use(this.validateUserHeader);
		this.router.use(this.validator.headers(headersSchema));
	}

	get(endpoint: string, controller: Function, validationSchema?: SchemaMap) {
		this.addRoute(RouteMethod.Get, endpoint, controller, validationSchema);
	}

	post(endpoint: string, controller: Function, validationSchema?: SchemaMap) {
		this.addRoute(RouteMethod.Post, endpoint, controller, validationSchema);
	}

	put(endpoint: string, controller: Function, validationSchema?: SchemaMap) {
		this.addRoute(RouteMethod.Put, endpoint, controller, validationSchema);
	}

	delete(endpoint: string, controller: Function, validationSchema?: SchemaMap) {
		this.addRoute(RouteMethod.Delete, endpoint, controller, validationSchema);
	}

	private addRoute(
		type: RouteMethod,
		endpoint: string,
		controller: Function,
		validationSchema?: SchemaMap,
	) {
		const validators = this.convertValidationSchema(validationSchema);

		this.router[type](endpoint, ...validators, async (req: any, res: any) => {
			try {
				const result = await controller(req);
				res.json(result);
			} catch (err) {
				if (err.type && err.type === 'ServiceError') {
					res.status(err.code).json({ error: err.error });
				}

				res.status(400).json(err);
			}
		});
	}

	private convertValidationSchema(schema?: SchemaMap): express.RequestHandler[] {
		if (!schema) {
			return [];
		}

		const keys = Object.keys(schema) as SchemaFields[];

		const validators = keys.map((key) => this.validator[key](schema[key] as Joi.Schema));

		return validators;
	}

	private validateUserHeader(
		req: express.Request,
		res: express.Response,
		next: express.NextFunction,
	) {
		if (!req.header(USER_HEADER)) {
			return res.status(401).end();
		}

		next();
	}
}

enum RouteMethod {
	Get = 'get',
	Post = 'post',
	Put = 'put',
	Delete = 'delete',
}
