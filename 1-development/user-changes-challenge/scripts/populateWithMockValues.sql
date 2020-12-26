\connect ixchallenge postgres;

insert into changes values (    
    1,
    '2020-12-23 23:32:10.257',
    '{
		"field": "name",
		"old": "Bruce Norris",
		"new": "Bruce Willis"
	}'
);

insert into changes values (    
    1,
    '2020-12-23 23:34:15.759',
    '{
		"field": "name",
		"old": "Bruce Willis",
		"new": "John Cena"
	}'
);

insert into changes values (    
    1,
    '2020-12-23 23:37:34.829',
    '{
		"field": "age",
		"old": 10,
		"new": 20
	}'
);

insert into changes values (    
    1,
    '2020-12-23 23:50:57.961',
    '{
        "field": "age",
        "old": 14,
        "new": 40
    }'
);