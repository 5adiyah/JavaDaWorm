# JavaDaWorm
Epicodus Java Final Group Project

###DATABASE CREATION
```
CREATE DATABASE java_da_worm;
\c java_da_worm;
CREATE TABLE errors (id serial PRIMARY KEY, name varchar, type varchar, tag varchar);
CREATE TABLE solutions(id serial PRIMARY KEY, name varchar, description text, tag varchar);
CREATE TABLE userinputs(id serial PRIMARY KEY, error text, solution text);
CREATE TABLE errors_solutions(id serial PRIMARY KEY, error_id int, solution_id int);
CREATE DATABASE java_da_worm_test WITH TEMPLATE java_da_worm;
```
