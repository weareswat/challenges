import * as path from 'path';

export const Config = {
	port: 8080,
	knex: {
		client: 'pg',
		connection: {
			host: process.env.DB_HOST,
			port: Number(process.env.DB_PORT) || 5432,
			user: process.env.DB_USER || 'postgres',
			password: process.env.DB_PASSWORD,
			database: process.env.DB_NAME || 'postgres',
		},
		debug: true,
		migrations: {
			directory: path.join(__dirname, 'migrations'),
			extension: 'ts',
		},
	},
};
