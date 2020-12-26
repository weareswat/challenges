\connect ixchallenge postgres;

drop table if exists changes;

create table changes (
    id integer not null,
    date timestamp not null default now(),
    diff json not null,
    primary key (id, date)
);