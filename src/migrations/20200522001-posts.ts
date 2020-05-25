import * as Knex from 'knex';
import { POSTS_TABLE } from '../constants';

export function up(knex: Knex) {
	return knex.schema.createTable(POSTS_TABLE, (table) => {
		table.uuid('id').notNullable().primary();
		table.string('title', 128).notNullable();
		table.string('user', 50).notNullable();
		table.dateTime('created_at').notNullable().defaultTo(knex.fn.now());
		table.integer('votes').notNullable().defaultTo(0);
		table.float('upvote_percentage').notNullable().defaultTo(0);
		table.float('downvote_percentage').notNullable().defaultTo(0);
	});
}

export function down(knex: Knex) {
	return knex.schema.dropTable(POSTS_TABLE);
}
