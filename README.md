# JAVA DA WORM
By: Sadio Ali, Kendra Dunham, Justin Fokes, Ming Wen

## Technologies Used

jUnit
Fluentleniu
Veloctiy
Java
HTML
CSS

## Setting Up Project

Clone this repository:

```
$ git clone https://github.com/5adiyah/JavaDaWorm.git
$ cd JavaDaWorm
```

Open terminal and run Postgres, then in a new tab run psql
```
$ postgres
$ psql
```

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

Navigate back to the directory where this repository has been cloned, then run gradle:
```
$ gradle run
```

## Legal

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

