\connect ixchallenge postgres;

drop table if exists changes;

create table changes (
    uuid serial primary key,
    userID integer not null,
    date timestamp not null default now(),
    diff json not null
);
