CREATE TABLE IF NOT EXISTS post_model (
    id INT AUTO_INCREMENT  PRIMARY KEY,
	title varchar(255) NOT NULL,
    votes_up integer NOT NULL,
    votes_down integer NOT NULL,
    votes_up_percentage varchar(6) NOT NULL,
    votes_down_percentage varchar(6) NOT NULL
);