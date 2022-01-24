--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nome character varying NOT NULL,
    cpf character(11) NOT NULL,
    data_cadastro date NOT NULL,
    cliente_premium boolean NOT NULL,
    idade integer NOT NULL,
    endereco character varying,
    telefone integer
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- Name: filme; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.filme (
    id_filme integer NOT NULL,
    idioma_original character varying NOT NULL,
    legenda boolean NOT NULL,
    duracao time without time zone NOT NULL,
    genero character varying NOT NULL,
    classificacao integer NOT NULL,
    titulo character varying NOT NULL,
    data_lancamento date NOT NULL
);


ALTER TABLE public.filme OWNER TO postgres;

--
-- Name: filme_id_filme_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.filme_id_filme_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.filme_id_filme_seq OWNER TO postgres;

--
-- Name: filme_id_filme_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.filme_id_filme_seq OWNED BY public.filme.id_filme;


--
-- Name: locacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.locacao (
    id_locacao integer NOT NULL,
    id_cliente_locacao integer NOT NULL,
    id_filme_locacao integer NOT NULL,
    data_locacao timestamp without time zone NOT NULL,
    preco real NOT NULL,
    data_devolucao date NOT NULL
);


ALTER TABLE public.locacao OWNER TO postgres;

--
-- Name: locacao_id_locacao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.locacao_id_locacao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.locacao_id_locacao_seq OWNER TO postgres;

--
-- Name: locacao_id_locacao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.locacao_id_locacao_seq OWNED BY public.locacao.id_locacao;


--
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: filme id_filme; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filme ALTER COLUMN id_filme SET DEFAULT nextval('public.filme_id_filme_seq'::regclass);


--
-- Name: locacao id_locacao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locacao ALTER COLUMN id_locacao SET DEFAULT nextval('public.locacao_id_locacao_seq'::regclass);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id_cliente, nome, cpf, data_cadastro, cliente_premium, idade, endereco, telefone) FROM stdin;
70	Amanda	423252     	2019-04-02	t	20	Rua n	1234
74	Paulo	23443223454	2019-05-02	t	23	Rua sadfds	2343232
64	Juulia	1233212333 	2019-02-04	f	2	rua d	2
67	Juliana	21323443289	2019-04-02	f	27	Rua y	12342134
61	Gabriel	000000000  	2019-01-31	t	2	Rua Tal	2
68	juliana	21332452490	2019-04-02	f	34	rua y	12344321
\.


--
-- Data for Name: filme; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.filme (id_filme, idioma_original, legenda, duracao, genero, classificacao, titulo, data_lancamento) FROM stdin;
9	Francês	t	02:02:00	fdfh	10	lslslsls	2004-04-06
15	saffd	f	03:00:00	fas	16	wqds	2002-02-02
16	rewtw	f	03:00:00	rar	16	saetr	2002-02-02
17	sdfsdf	t	03:00:00	fsdgsd	16	sdf	2002-02-02
20	ksjskjsksj	t	03:00:00	ksjskjsksj	16	ksjskjsksj	2019-04-03
21	Inglês	t	01:30:00	Suspense	16	O filme y	2017-02-12
24	gddfg	t	03:00:00	sdsdf	12	Filme 007	2005-03-04
22	bbbbbbb	f	01:00:00	aaaaaqaa	2	bbbbbbb	2001-01-01
\.


--
-- Data for Name: locacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.locacao (id_locacao, id_cliente_locacao, id_filme_locacao, data_locacao, preco, data_devolucao) FROM stdin;
26	67	24	2005-03-04 00:00:00	3	2007-05-06
\.


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 74, true);


--
-- Name: filme_id_filme_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.filme_id_filme_seq', 24, true);


--
-- Name: locacao_id_locacao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.locacao_id_locacao_seq', 26, true);


--
-- Name: cliente cliente_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pk PRIMARY KEY (id_cliente);


--
-- Name: filme filme_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filme
    ADD CONSTRAINT filme_pk PRIMARY KEY (id_filme);


--
-- Name: locacao locacao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locacao
    ADD CONSTRAINT locacao_pk PRIMARY KEY (id_locacao, id_cliente_locacao, id_filme_locacao);


--
-- Name: locacao cliente_locacao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locacao
    ADD CONSTRAINT cliente_locacao_fk FOREIGN KEY (id_cliente_locacao) REFERENCES public.cliente(id_cliente);


--
-- Name: locacao filme_locacao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locacao
    ADD CONSTRAINT filme_locacao_fk FOREIGN KEY (id_filme_locacao) REFERENCES public.filme(id_filme);


--
-- PostgreSQL database dump complete
--

