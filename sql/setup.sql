/* Drop everything */
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

/* create users */
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(255) NOT NULL,
  passhash VARCHAR(255) NOT NULL,
  CONSTRAINT PK_users PRIMARY KEY (username)
);

/* create todos */
CREATE TABLE IF NOT EXISTS todos (
  todo_id   BIGSERIAL    NOT NULL,
  todo      VARCHAR(255) NOT NULL,
  completed BOOLEAN      NOT NULL,
  owner     VARCHAR(255) NOT NULL,
  CONSTRAINT PK_todos PRIMARY KEY (todo_id),
  CONSTRAINT FK_todos_owner FOREIGN KEY (owner) REFERENCES users (username)
);