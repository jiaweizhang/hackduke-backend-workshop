/* Drop everything */
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

/* create todos */
CREATE TABLE IF NOT EXISTS todos (
  todo_id BIGSERIAL    NOT NULL,
  todo    VARCHAR(255) NOT NULL
);