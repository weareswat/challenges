import * as Knex from 'knex';
import { Config } from '../config';
import { Errors } from '../errors';
import { buildServiceError } from './error-builder';

let instance: Knex | undefined;

export type Transaction = Knex.Transaction | undefined;

export async function init() {
	await runMigrations();
}

function getConnection(): Knex {
	return instance || Knex(Config.knex);
}

export async function tryCatch<T>(trx: Transaction, cb: (conn: Knex) => Promise<T>): Promise<T> {
	try {
		return await cb(trx || getConnection());
	} catch (e) {
		throw buildServiceError(Errors.DatabaseError, 400, e);
	}
}

async function getTransaction(): Promise<Knex.Transaction> {
	const connection = getConnection();

	return new Promise<Knex.Transaction>((resolve, reject) => {
		try {
			connection.transaction((trx) => {
				resolve(trx);
			});
		} catch (err) {
			reject(err);
		}
	});
}

export async function withinTransaction<T>(
	execute: (trx: Knex.Transaction) => Promise<T>,
): Promise<T> {
	const trx = await getTransaction();

	try {
		const data = await execute(trx);

		await trx.commit();

		return data;
	} catch (err) {
		await trx.rollback(err);

		throw err;
	}
}

export async function upsert(args: {
	conn: Knex;
	tableName: string;
	insertObject: any;
	updateObject: any;
	conflictColumns: string[];
}) {
	const insertQuery = args.conn.table(args.tableName).insert(args.insertObject);

	const updateQuery = args.conn.queryBuilder().update(args.updateObject);

	return args.conn
		.raw(`? ON CONFLICT (${args.conflictColumns.map((k) => `"${k}"`).join(', ')}) DO ?`, [
			insertQuery,
			updateQuery,
		])
		.then();
}

async function runMigrations() {
	getConnection().migrate.latest();
}
