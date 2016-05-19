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
-- Name: userinputs; Type: TABLE; Schema: public; Owner: kendra
--

CREATE TABLE userinputs (
    id integer NOT NULL,
    error text,
    solution text
);


ALTER TABLE userinputs OWNER TO kendra;

--
-- Name: userinputs_id_seq; Type: SEQUENCE; Schema: public; Owner: kendra
--

CREATE SEQUENCE userinputs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE userinputs_id_seq OWNER TO kendra;

--
-- Name: userinputs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kendra
--

ALTER SEQUENCE userinputs_id_seq OWNED BY userinputs.id;


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
-- Name: id; Type: DEFAULT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY userinputs ALTER COLUMN id SET DEFAULT nextval('userinputs_id_seq'::regclass);


--
-- Data for Name: errors; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY errors (id, name, type, tag) FROM stdin;
9	INSTANTIATION ERROR	pre	instantiation
10	MISPELLINGS	pre	typos
11	FORMAL TYPOS	pre	typos
12	STATIC METHODS	pre	semi-colon
14	FILE ERRORS	pre	file
15	PSQLException	pre	psql-error
16	CANNOT FIND SYMBOL	pre	cannot-find
17	GRADLE ERROR	pre	gradle
18	VARIABLES	pre	variables
19	500 ERROR	post	500
20	400 ERROR	post	400
21	NULL POINTER EXCEPTION	post	null
22	CANNOT FIND SYMBOL	post	cannot-find
23	VARIABLES	post	variables
24	MISPELLINGS	post	typos
25	FORMAL TYPOS	post	typos
26	SPARK	post	spark
\.


--
-- Name: errors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('errors_id_seq', 26, true);


--
-- Data for Name: errors_solutions; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY errors_solutions (id, error_id, solution_id) FROM stdin;
20	9	20
22	9	22
23	9	23
24	9	24
25	9	25
26	10	26
27	10	27
28	10	28
29	10	29
30	10	30
31	10	31
32	11	32
33	11	33
34	11	34
35	11	35
36	11	36
38	12	38
39	12	39
40	12	40
41	12	41
42	12	42
43	16	43
44	16	44
45	16	45
46	16	46
47	16	47
48	14	48
49	14	49
50	14	50
51	14	51
52	15	52
53	15	53
55	15	55
56	15	56
57	15	57
58	15	58
59	17	59
60	17	60
61	17	61
62	17	62
63	17	63
64	18	64
65	18	65
66	18	66
67	18	67
68	18	68
69	18	69
70	19	70
71	19	71
72	19	72
73	19	73
74	19	74
75	20	75
76	20	76
77	20	77
78	20	78
79	21	79
80	21	80
81	21	81
82	21	82
83	21	83
84	22	84
85	22	85
86	22	86
87	22	87
88	22	88
89	22	89
90	23	90
91	23	91
92	23	92
93	23	93
94	24	94
95	24	95
96	24	96
97	24	97
98	24	98
99	24	99
100	25	100
101	25	101
102	25	102
103	25	103
104	25	104
106	26	106
107	26	107
108	26	108
109	26	109
110	26	110
111	26	111
112	26	112
113	26	113
114	26	114
\.


--
-- Name: errors_solutions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('errors_solutions_id_seq', 114, true);


--
-- Data for Name: solutions; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY solutions (id, name, description, tag) FROM stdin;
20	INSTANTIATION SOLUTION	Do you have the right number of inputs for your method?	instantiation
22	INSTANTIATION SOLUTION	Have you defined all your variable types?	instantiation
23	INSTANTIATION SOLUTION	Are the inputs the correct variable type?	instantiation
24	INSTANTIATION SOLUTION	Check your terminal, dude. I can't fix everything for you.	instantiation
25	INSTANTIATION SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	instantiation
26	MISPELLINGS SOLUTION	asserThat? Did you mean assertThat?	typos
27	MISPELLINGS SOLUTION	Are your variables called what you think they're called?	typos
28	MISPELLINGS SOLUTION	Did you add an 's' where you shouldn't? You did, didn't you.	typos
29	MISPELLINGS SOLUTION	DiD yOu CaPiTaLiZe EvErYtHiNg CoRrEcTlY?!?	typos
30	MISPELLINGS SOLUTION	Check your terminal, dude. I can't fix everything for you.	typos
31	MISPELLINGS SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	typos
32	FORMAL TYPO SOLUTIONS	You're probably missing a semi-colon. It's always the semi-colons.	typos
33	FORMAL TYPO SOLUTIONS	Are all your parentheses closed? I bet they're not.	typos
34	FORMAL TYPO SOLUTIONS	Are all your curly-brackets closed? Is there a more professional word for those? These things: { }.	typos
35	FORMAL TYPO SOLUTIONS	Check your terminal, dude. I can't fix everything for you.	typos
36	FORMAL TYPO SOLUTIONS	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	typos
38	STATIC METHODS SOLUTIONS	Static methods expect returns. Did you return anything? Anything at all?	static
39	STATIC METHODS SOLUTIONS	You probably added a return for a non-static method. Ya don't need to.	static
40	STATIC METHODS SOLUTIONS	Did you actually need that to be static? That word may not mean what you think it means.	static
41	STATIC METHODS SOLUTIONS	Check your terminal, dude. I can't fix everything for you.	static
42	STATIC METHODS SOLUTIONS	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	static
43	CANNOT FIND SYMBOL SOLUTION	Have you imported all your dependencies? Java isn't a mind reader.	cannot-find
44	CANNOT FIND SYMBOL SOLUTION	I know you think it exists, but it doesn't. You probably spelled something wrong. Or you just forgot to write an entire method. Dummy.	cannot-find
45	CANNOT FIND SYMBOL SOLUTION	Copy and paste is not your friend. Does everything you're testing exist, in your current application, in the file you're testing? This is your moment of zen.	cannot-find
46	CANNOT FIND SYMBOL SOLUTION	Check your terminal, dude. I can't fix everything for you.	cannot-find
47	CANNOT FIND SYMBOL SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	cannot-find
48	FILE ERROR SOLUTIONS	 Is your class declared in your file name? CaSe MaTtErS. Extras s's ares a problems.	file
49	FILE ERROR SOLUTIONS	You're probably using a template. Did you change your database name in your Database file and update your DataBase rule?	file
50	FILE ERROR SOLUTIONS	Check your terminal, dude. I can't fix everything for you.	file
51	FILE ERROR SOLUTIONS	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	file
52	PSQLException SOLUTION	Does that table exist? No, really, I know you think it does. Did you spell it right? Java ain't a mind reader.	psql-error
53	PSQLException SOLUTION	Did you create a test database? Do you know how? Here goes:\r\n  CREATE DATABASE database_test WITH TEMPLATE database; *REMEMBER THE SEMICOLON*	psql-error
55	PSQLException SOLUTION	When you created your tables, were you actually in your database? If you're floating out in the sql nether, the tables won't apply correctly. Change to your database with \\c database_name;	psql-error
56	PSQLException SOLUTION	When you were creating your database, did you close all your semi-colons or did you just keep carrying on with your ADDs and your DELETEs like sql didn't care.	psql-error
57	PSQLException SOLUTION	Check your terminal, dude. I can't fix everything for you.	psql-error
58	PSQLException SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	psql-error
59	GRADLE ERROR SOLUTIONS	Is gradle already running in another window? It's too stupid to do more than one thing at a time. Be gentle with it.	gradle
60	GRADLE ERROR SOLUTIONS	Do you *have* a build.gradle file? Does it say what you think it says?	gradle
61	GRADLE ERROR SOLUTIONS	Have you rebuilt your build folder? Sometimes gradle gets confused.	gradle
62	GRADLE ERROR SOLUTIONS	Check your terminal, dude. I can't fix everything for you.	gradle
63	GRADLE ERROR SOLUTIONS	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	gradle
64	VARIABLE SOLUTION	I have to ask, is the variable actually what you think it is? CaSe MaTtErS. Extras s's ares a problems.	variables
65	VARIABLE SOLUTION	Java cares. Specifically, Java cares about data types remaining the same. Did YOU care?	variables
66	VARIABLE SOLUTION	Did you actually instantiate the variable correctly? Like, with a type, and with a meaning? Please give your variable meaning. And then close it off with a semi-colon.	variables
67	VARIABLE SOLUTION	Check your terminal, dude. I can't fix everything for you.	variables
68	VARIABLE SOLUTION	Does that variable already exist? If you use the same one without meaning to, you give the poor thing an identity crisis. It just wants to know what you want.	variables
69	VARIABLE SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	variables
70	500 ERROR SOLUTION	Your App thinks something exists. It doesn't. You probably spelled something wrong. Go fix it.	500
71	500 ERROR SOLUTION	Did you #end all your #if and #foreach statements? If you didn't you broke it.	500
72	500 ERROR SOLUTION	I know we're on the honor system on whether or not you've already tested your methods, but even if you did, and they're passing, they're not doing what you think they're doing?	500
73	500 ERROR SOLUTION	Check your terminal, dude. I can't fix everything for you.	500
74	500 ERROR SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	500
75	400 ERROR SOLUTION	You think you're sending the user somewhere but that place doesn't exist. Your program is trying to do your bidding, but it has no idea what you're talking about. Check your routes, yo. Help a brother out.	400
76	400 ERROR SOLUTION	Your link is broken. Go fix it. It's ok, I'll wait.	400
77	400 ERROR SOLUTION	FilE PatheS ArE SensItiVe to CasE and missspelings. If you typed them right the first time we wouldn't be having this conversation.	400
78	400 ERROR SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	400
79	NULL POINTER SOLUTION	You know you can't call class-specific methods on a different class, right? If you need that method on a different class, you need to create it there. Don't copy and paste! Copy and paste is the enemy!	null
80	NULL POINTER SOLUTION	Java cares. Specifically, Java cares about data types remaining the same. Did YOU care?	null
81	NULL POINTER SOLUTION	Did you skip a step? Did you try to use an instance of your class without using a save function, for example?	null
82	NULL POINTER SOLUTION	Check your terminal, dude. I can't fix everything for you.	null
83	NULL POINTER SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	null
84	CANNOT FIND SYMBOL SOLUTION	Did you call your methods and variables what you think you called them? Did you actually type that into your code?	cannot-find
85	CANNOT FIND SYMBOL SOLUTION	Have you imported all your dependencies? Java isn't a mind reader.	cannot-find
86	CANNOT FIND SYMBOL SOLUTION	I know you think it exists, but it doesn't. You probably spelled something wrong. Or you just forgot to write an entire method. Dummy.	cannot-find
87	CANNOT FIND SYMBOL SOLUTION	Copy and paste is not your friend. Does everything you're testing exist, in your current application, in the file you're testing? This is your moment of zen.	cannot-find
88	CANNOT FIND SYMBOL SOLUTION	Check your terminal, dude. I can't fix everything for you.	cannot-find
89	CANNOT FIND SYMBOL SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	cannot-find
90	VARIABLE SOLUTION	I have to ask, is the variable actually what you think it is? CaSe MaTtErS. Extras s's ares a problems.	variables
91	VARIABLE SOLUTION	Java cares. Specifically, Java cares about data types remaining the same. Did YOU care?\r\n  Solution: Check your terminal, dude. I can't fix everything for you.	variables
92	VARIABLE SOLUTION	Does that variable already exist? If you use the same one without meaning to, you give the poor thing an identity crisis. It just wants to know what you want.	variables
93	VARIABLE SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	variables
94	MISPELLINGS SOLUTION	asserThat? Did you mean assertThat?	typos
95	MISPELLINGS SOLUTION	Are your variables called what you think they're called?	typos
96	MISPELLINGS SOLUTION	Did you add an 's' where you shouldn't? You did, didn't you.	typos
97	MISPELLINGS SOLUTION	DiD yOu CaPiTaLiZe EvErYtHiNg CoRrEcTlY?!?	typos
98	MISPELLINGS SOLUTION	Check your terminal, dude. I can't fix everything for you.	typos
99	MISPELLINGS SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	typos
100	FORMAL TYPO SOLUTIONS	You're probably missing a semi-colon. It's always the semi-colons.	typos
101	FORMAL TYPO SOLUTIONS	Are all your parentheses closed? I bet they're not.	typos
102	FORMAL TYPO SOLUTIONS	Are all your curly-brackets closed? Is there a more professional word for those? These things: { }.	typos
103	FORMAL TYPO SOLUTIONS	Check your terminal, dude. I can't fix everything for you.	typos
104	FORMAL TYPO SOLUTIONS	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	typos
106	SPARK SOLUTION	Check yo' routes yo. Is your page going where your App thinks it's going?	spark
107	SPARK SOLUTION	Are you GETting when you should be POSTing?	spark
108	SPARK SOLUTION	model.put is your friend. If you don't put it into the page, the page thinks you're talking nonsense.	spark
109	SPARK SOLUTION	RESTful routing RESTful routing RESTful routing RESTful routing RESTful routing RESTful routing RESTful routing... have I gotten my point across yet?	spark
110	SPARK SOLUTION	Are your params querying the name element? Spark and input IDs had a fight a while back and now they're not talking to each other.	spark
111	SPARK SOLUTION	Check yo' file names yo. Did you never create that .vtl file? It was 4:45 and you wanted to sign out huh.	spark
112	SPARK SOLUTION	Did you remember those dolla' dollas $$$$$$$??????	spark
113	SPARK SOLUTION	Have you tried this new thing, I think, it's called, like, Google? Like, maybe use that? And stop bothering me about it?	spark
114	SPARK SOLUTION	Check your terminal, dude. I can't fix everything for you.	spark
\.


--
-- Name: solutions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('solutions_id_seq', 114, true);


--
-- Data for Name: userinputs; Type: TABLE DATA; Schema: public; Owner: kendra
--

COPY userinputs (id, error, solution) FROM stdin;
\.


--
-- Name: userinputs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kendra
--

SELECT pg_catalog.setval('userinputs_id_seq', 1, false);


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
-- Name: userinputs_pkey; Type: CONSTRAINT; Schema: public; Owner: kendra
--

ALTER TABLE ONLY userinputs
    ADD CONSTRAINT userinputs_pkey PRIMARY KEY (id);


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

