import * as Knex from 'knex';
import { VOTES_TABLE, POSTS_TABLE } from '../constants';

export function up(knex: Knex) {
	return knex.schema.createTable(VOTES_TABLE, (table) => {
		table.uuid('post_id').notNullable();
		table.string('user', 50).notNullable();
		table.dateTime('voted_at').notNullable().defaultTo(knex.fn.now());
		table.integer('vote').notNullable();

		table.primary(['post_id', 'user']);
		table.foreign('post_id').references('id').inTable(POSTS_TABLE);
	});
}

export function down(knex: Knex) {
	return knex.schema.dropTable(VOTES_TABLE);
}
