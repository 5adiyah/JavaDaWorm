--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: errors; Type: TABLE; Schema: public; Owner: kendra
--

CREATE TABLE errors (
    id integer NOT NULL,
    name character varying,
    type character varying,
    tag character varying
);


ALTER TABLE errors OWNER TO kendra;

--
-- Name: errors_id_seq; Type: SEQUENCE; Schema: public; Owner: kendra
--

CREATE SEQUENCE errors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE errors_id_seq OWNER TO kendra;

--
-- Name: errors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kendra
--

ALTER SEQUENCE errors_id_seq OWNED BY errors.id;


--
-- Name: errors_solutions; Type: TABLE; Schema: public; Owner: kendra
--

CREATE TABLE errors_solutions (
    id integer NOT NULL,
    error_id integer,
    solution_id integer
);


ALTER TABLE errors_solutions OWNER TO kendra;

--
-- Name: errors_solutions_id_seq; Type: SEQUENCE; Schema: public; Owner: kendra
--

CREATE SEQUENCE errors_solutions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE errors_solutions_id_seq OWNER TO kendra;

--
-- Name: errors_solutions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kendra
--

ALTER SEQUENCE errors_solutions_id_seq OWNED BY errors_solutions.id;


--
-- Name: solutions; Type: TABLE; Schema: public; Owner: kendra
--

CREATE TABLE solutions (
    id integer NOT NULL,
    name character varying,
    description text,
    tag character varying
);


ALTER TABLE solutions OWNER TO kendra;

--
-- Name: solutions_id_seq; Type: SEQUENCE; Schema: public; Owner: kendra
--

CREATE SEQUENCE solutions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE solutions_id_seq OWNER TO kendra;

--
-- Name: solutions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kendra
--

ALTER SEQUENCE solutions_id_seq OWNED BY solutions.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY errors ALTER COLUMN id SET DEFAULT nextval('errors_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY errors_solutions ALTER COLUMN id SET DEFAULT nextval('errors_solutions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY solutions ALTER COLUMN id SET DEFAULT nextval('solutions_id_seq'::regclass);


--
-- Data for Name: errors; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY errors (id, name, type, tag) FROM stdin;
\.


--
-- Name: errors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('errors_id_seq', 1, false);


--
-- Data for Name: errors_solutions; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY errors_solutions (id, error_id, solution_id) FROM stdin;
\.


--
-- Name: errors_solutions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('errors_solutions_id_seq', 1, false);


--
-- Data for Name: solutions; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY solutions (id, name, description, tag) FROM stdin;
\.


--
-- Name: solutions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('solutions_id_seq', 1, false);


--
-- Name: errors_pkey; Type: CONSTRAINT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY errors
    ADD CONSTRAINT errors_pkey PRIMARY KEY (id);


--
-- Name: errors_solutions_pkey; Type: CONSTRAINT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY errors_solutions
    ADD CONSTRAINT errors_solutions_pkey PRIMARY KEY (id);


--
-- Name: solutions_pkey; Type: CONSTRAINT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY solutions
    ADD CONSTRAINT solutions_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: kendra
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM kendra;
GRANT ALL ON SCHEMA public TO kendra;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

