
CREATE TABLE actor (
  id INTEGER NOT NULL PRIMARY KEY,
  description varchar(255),
  first_name varchar(50)
);
CREATE INDEX actor_description ON actor(description);