DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
  uuid INT AUTO_INCREMENT PRIMARY KEY,
  userid VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  month VARCHAR(250) NOT NULL
);

INSERT INTO USER (userid, name, address, month) VALUES
  ('1', 'Bruce', 'Bill Indus', 'MARCH'),
  ('1', 'Gates', 'Billionaire Tech ', 'AUGUST'),
  ('1', 'Gates', 'Billionaire Entre', 'AUGUST'),
  ('1', 'Alakija', 'Billionaire Oil Magnate', 'AUGUST');