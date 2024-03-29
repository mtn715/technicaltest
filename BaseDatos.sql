--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-03-29 00:01:49

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE dbtechnicaltest;
--
-- TOC entry 4809 (class 1262 OID 16397)
-- Name: dbtechnicaltest; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE dbtechnicaltest WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';


ALTER DATABASE dbtechnicaltest OWNER TO postgres;

\connect dbtechnicaltest

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 17169)
-- Name: tcuentas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tcuentas (
    estado boolean,
    saldo_inicial double precision,
    client_id bigint,
    numero_cuenta bigint NOT NULL,
    tipo character varying(255)
);


ALTER TABLE public.tcuentas OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 17168)
-- Name: tcuentas_numero_cuenta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tcuentas_numero_cuenta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tcuentas_numero_cuenta_seq OWNER TO postgres;

--
-- TOC entry 4810 (class 0 OID 0)
-- Dependencies: 215
-- Name: tcuentas_numero_cuenta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tcuentas_numero_cuenta_seq OWNED BY public.tcuentas.numero_cuenta;


--
-- TOC entry 218 (class 1259 OID 17176)
-- Name: tmovimientos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tmovimientos (
    estado boolean,
    saldo double precision,
    valor double precision,
    fecha_movimiento date,
    movimiento_id bigint NOT NULL,
    numero_cuenta bigint NOT NULL,
    tipo_movimiento character varying(255)
);


ALTER TABLE public.tmovimientos OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17175)
-- Name: tmovimientos_movimiento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tmovimientos_movimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tmovimientos_movimiento_id_seq OWNER TO postgres;

--
-- TOC entry 4811 (class 0 OID 0)
-- Dependencies: 217
-- Name: tmovimientos_movimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tmovimientos_movimiento_id_seq OWNED BY public.tmovimientos.movimiento_id;


--
-- TOC entry 220 (class 1259 OID 17232)
-- Name: tusuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tusuarios (
    edad integer,
    estado boolean,
    cliente_id bigint NOT NULL,
    identificacion bigint NOT NULL,
    contrasena character varying(255),
    direccion character varying(255),
    genero character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);


ALTER TABLE public.tusuarios OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17231)
-- Name: tusuarios_cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tusuarios_cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tusuarios_cliente_id_seq OWNER TO postgres;

--
-- TOC entry 4812 (class 0 OID 0)
-- Dependencies: 219
-- Name: tusuarios_cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tusuarios_cliente_id_seq OWNED BY public.tusuarios.cliente_id;


--
-- TOC entry 4644 (class 2604 OID 17172)
-- Name: tcuentas numero_cuenta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tcuentas ALTER COLUMN numero_cuenta SET DEFAULT nextval('public.tcuentas_numero_cuenta_seq'::regclass);


--
-- TOC entry 4645 (class 2604 OID 17179)
-- Name: tmovimientos movimiento_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tmovimientos ALTER COLUMN movimiento_id SET DEFAULT nextval('public.tmovimientos_movimiento_id_seq'::regclass);


--
-- TOC entry 4646 (class 2604 OID 17235)
-- Name: tusuarios cliente_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tusuarios ALTER COLUMN cliente_id SET DEFAULT nextval('public.tusuarios_cliente_id_seq'::regclass);


--
-- TOC entry 4799 (class 0 OID 17169)
-- Dependencies: 216
-- Data for Name: tcuentas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tcuentas (estado, saldo_inicial, client_id, numero_cuenta, tipo) VALUES (true, 1200, 1, 2, 'Ahorros');
INSERT INTO public.tcuentas (estado, saldo_inicial, client_id, numero_cuenta, tipo) VALUES (true, 1200, 1, 1, 'Corriente');


--
-- TOC entry 4801 (class 0 OID 17176)
-- Dependencies: 218
-- Data for Name: tmovimientos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tmovimientos (estado, saldo, valor, fecha_movimiento, movimiento_id, numero_cuenta, tipo_movimiento) VALUES (true, 200, 1000, '2024-03-28', 1, 2, 'Deposito');
INSERT INTO public.tmovimientos (estado, saldo, valor, fecha_movimiento, movimiento_id, numero_cuenta, tipo_movimiento) VALUES (true, 200, 1000, '2024-03-28', 2, 1, 'Deposito');


--
-- TOC entry 4803 (class 0 OID 17232)
-- Dependencies: 220
-- Data for Name: tusuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tusuarios (edad, estado, cliente_id, identificacion, contrasena, direccion, genero, nombre, telefono) VALUES (32, true, 1, 72803597, '1234567', 'calle 123', 'Masculino', 'Martin', '954106207');


--
-- TOC entry 4813 (class 0 OID 0)
-- Dependencies: 215
-- Name: tcuentas_numero_cuenta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tcuentas_numero_cuenta_seq', 2, true);


--
-- TOC entry 4814 (class 0 OID 0)
-- Dependencies: 217
-- Name: tmovimientos_movimiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tmovimientos_movimiento_id_seq', 2, true);


--
-- TOC entry 4815 (class 0 OID 0)
-- Dependencies: 219
-- Name: tusuarios_cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tusuarios_cliente_id_seq', 1, true);


--
-- TOC entry 4648 (class 2606 OID 17174)
-- Name: tcuentas tcuentas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tcuentas
    ADD CONSTRAINT tcuentas_pkey PRIMARY KEY (numero_cuenta);


--
-- TOC entry 4650 (class 2606 OID 17181)
-- Name: tmovimientos tmovimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tmovimientos
    ADD CONSTRAINT tmovimientos_pkey PRIMARY KEY (movimiento_id);


--
-- TOC entry 4652 (class 2606 OID 17241)
-- Name: tusuarios tusuarios_identificacion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tusuarios
    ADD CONSTRAINT tusuarios_identificacion_key UNIQUE (identificacion);


--
-- TOC entry 4654 (class 2606 OID 17239)
-- Name: tusuarios tusuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tusuarios
    ADD CONSTRAINT tusuarios_pkey PRIMARY KEY (cliente_id);


-- Completed on 2024-03-29 00:01:49

--
-- PostgreSQL database dump complete
--

