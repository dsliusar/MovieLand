DROP TABLE IF EXISTS genre;
create table genre(
  genre_id int not null AUTO_INCREMENT,
  description VARCHAR(1000),
  PRIMARY KEY (genre_id)
);

DROP TABLE IF EXISTS genre_movies;
CREATE TABLE genre_movies (
  genre_movie_id INT NOT NULL,
  movie_id INT NOT NULL,
  genre_id INT,
  PRIMARY KEY (genre_movie_id)
);

DROP TABLE IF EXISTS movie;

CREATE table movie(
   movie_id INT NOT NULL AUTO_INCREMENT
  ,movie_name_rus VARCHAR(100)
  ,movie_name_eng VARCHAR(100)
  ,year INT
  ,description VARCHAR(5000)
  ,rating DOUBLE
  ,price DOUBLE
  ,PRIMARY KEY (movie_id)
);

DROP TABLE IF EXISTS genre_movies;
CREATE TABLE genre_movies (
  genre_movie_id INT NOT NULL AUTO_INCREMENT,
  movie_id INT NOT NULL,
  genre_id INT,
  PRIMARY KEY (genre_movie_id)
);

DROP TABLE IF EXISTS review;
CREATE TABLE review(
  review_id INT NOT NULL AUTO_INCREMENT,
  movie_id INT,
  user_id INT,
  review_text VARCHAR(5000),
  PRIMARY KEY (review_id)
);

DROP TABLE IF EXISTS countries;
CREATE TABLE countries(
  country_id INT NOT NULL AUTO_INCREMENT,
  country_name VARCHAR(50),
  PRIMARY KEY (country_id)
);

DROP TABLE IF EXISTS countries_movie_mapper;
CREATE TABLE countries_movie_mapper(
  country_movie_id INT NOT NULL AUTO_INCREMENT,
  country_id INT,
  movie_id INT,
  PRIMARY KEY (country_movie_id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user(
  user_id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(100),
  user_email VARCHAR(100),
  user_password VARCHAR(50),
  user_role VARCHAR(20) DEFAULT "user",
  PRIMARY KEY (user_id)
);

