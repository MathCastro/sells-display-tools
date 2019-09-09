CREATE TABLE TB_USER(
    user_id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE TB_SELLS(
    sell_id serial PRIMARY KEY,
    value VARCHAR(50),
    name VARCHAR(50),
    user_id integer NOT NULL,
    CONSTRAINT tb_sells_user_fkey FOREIGN KEY(user_id) REFERENCES TB_USER(user_id)
);