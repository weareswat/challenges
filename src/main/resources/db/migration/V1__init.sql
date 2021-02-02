CREATE TABLE IF NOT EXISTS post_model (
    id INT AUTO_INCREMENT  PRIMARY KEY,
	title varchar(255) NOT NULL,
    votes_up integer NOT NULL,
    votes_down integer NOT NULL,
    votes_up_percentage varchar(6) NOT NULL,
    votes_down_percentage varchar(6) NOT NULL
);

INSERT INTO post_model (id, title, votes_up, votes_down, votes_up_percentage, votes_down_percentage) VALUES
(1, 'Numero de casos covid', 600, 400, '6%', '4%'),
(2, 'Big data - futuro ou nao?', 6, 4, '6%', '4%')