/* Drop everything */
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

/* create users */
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(255) NOT NULL,
  passhash VARCHAR(255) NOT NULL,

);

/* create todos */
CREATE TABLE IF NOT EXISTS todos (
  todo_id   BIGSERIAL    NOT NULL,
  todo      VARCHAR(255) NOT NULL,
  completed BOOLEAN      NOT NULL,
  owner     VARCHAR(255) NOT NULL
);