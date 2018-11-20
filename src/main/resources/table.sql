CREATE TABLE author
(
  id        SERIAL NOT NULL
    CONSTRAINT author_pkey
    PRIMARY KEY,
  last_name VARCHAR(255),
  name      VARCHAR(255)
);

CREATE TABLE book
(
  id        SERIAL NOT NULL
    CONSTRAINT book_pkey
    PRIMARY KEY,
  genre     VARCHAR(255),
  title     VARCHAR(255),
  author_id INTEGER
    CONSTRAINT fkklnrv3weler2ftkweewlky958
    REFERENCES author
);

CREATE TABLE catalogue_user
(
  id            SERIAL       NOT NULL
    CONSTRAINT catalogue_user_pkey
    PRIMARY KEY,
  email         VARCHAR(255)
    CONSTRAINT uk_i9wsnfkx1o3rxrwypoqs4wgb9
    UNIQUE,
  hash_password VARCHAR(255) NOT NULL,
  login         VARCHAR(255) NOT NULL,
  role          VARCHAR(255),
  token         VARCHAR(255)
);

CREATE TABLE user_book
(
  id          SERIAL NOT NULL
    CONSTRAINT user_book_pkey
    PRIMARY KEY,
  book_status VARCHAR(255),
  book_id     INTEGER
    CONSTRAINT fk85pwltn867pjxog1gk5smtqcw
    REFERENCES book,
  user_id     INTEGER
    CONSTRAINT fk1i1br2pdxxp27t904khige7wl
    REFERENCES catalogue_user
);

