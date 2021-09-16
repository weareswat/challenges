drop database IF EXISTS listpostsbyratingdb;
drop user IF EXISTS listpostsbyrating;

create user listpostsbyrating with password 'password';
create database listpostsbyratingdb with template=template0 owner=listpostsbyrating;

\connect listpostsbyratingdb
alter default privileges grant all on tables to listpostsbyrating;
alter default privileges grant all on sequences to listpostsbyrating;

create table posts (
post_id integer primary key not null,
post_body text not null,
upvotes integer not null,
downvotes integer not null,
total_votes integer not null
);

create sequence posts_seq increment 1 start 1;

INSERT INTO posts(post_id, post_body, upvotes, downvotes, total_votes) VALUES(NEXTVAL('posts_seq'), 'This is Post 1.', 60, 40, 100);
INSERT INTO posts(post_id, post_body, upvotes, downvotes, total_votes) VALUES(NEXTVAL('posts_seq'), 'This is Post 2.', 600, 400, 1000);
INSERT INTO posts(post_id, post_body, upvotes, downvotes, total_votes) VALUES(NEXTVAL('posts_seq'), 'This is Post 3.', 0, 400, 400);
INSERT INTO posts(post_id, post_body, upvotes, downvotes, total_votes) VALUES(NEXTVAL('posts_seq'), 'This is Post 4.', 6, 4, 10);
