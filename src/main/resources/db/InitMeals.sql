DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS meals_seq;

CREATE SEQUENCE  meals_seq START WITH 100;

CREATE TABLE meals (
    id       INTEGER PRIMARY KEY DEFAULT  nextval('meals_seq'),
    user_id  INTEGER NOT NULL,
    dateTime TIMESTAMP NOT NULL,
    description VARCHAR (200) NOT NULL,
    calories TIMESTAMP NOT NULL,
    CONSTRAINT user_meal_id UNIQUE (user_id, dateTime),
    FOREIGN KEY (user_id) REFERENCES users(id);
);