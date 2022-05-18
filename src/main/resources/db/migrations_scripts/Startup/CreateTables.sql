BEGIN TRANSACTION;

DROP TABLE if EXISTS t_post CASCADE;
CREATE TABLE if NOT EXISTS t_post(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    up_votes BIGINT DEFAULT 0 NOT NULL,
    down_votes BIGINT DEFAULT 0 NOT NULL,

    -- Audit Columns
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    created_by  BIGINT                                 NOT NULL,
    deleted     BOOLEAN   DEFAULT FALSE                NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    updated_by  BIGINT,

    -- Constraints
    CONSTRAINT pk_post_id PRIMARY KEY (id)
);


COMMIT;