BEGIN TRANSACTION;

INSERT INTO t_post
(title, slug, content, up_votes, down_votes, created_by) VALUES
    ('Hello', 'hello', 'This is my first post.', 600, 400, 0), ('World', 'world', 'This is my second post', 60, 40, 0);

COMMIT;