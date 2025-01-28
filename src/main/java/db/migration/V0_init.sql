--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.25
-- Dumped by pg_dump version 9.5.25

-- Started on 2025-01-27 13:40:38

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2374 (class 1262 OID 16393)
-- Name: loja_virtual; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE loja_virtual WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE loja_virtual OWNER TO postgres;

\connect loja_virtual

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 223 (class 1255 OID 32885)
-- Name: validationpersonpk(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validationpersonpk() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare existe integer;

BEGIN
	existe = (select count(1) from physics_person where id = NEW.person_id);
	if(existe <= 0) then
	   existe = (select count(1) from juridic_person where id = NEW.company_id);
	if(existe <= 0) then
	   raise exception 'Não foi encontrado o ID ou PK da Pessoa para realizar Associação';
	   end if;
	end if;

	RETURN NEW;

END
$$;


ALTER FUNCTION public.validationpersonpk() OWNER TO postgres;

--
-- TOC entry 224 (class 1255 OID 32886)
-- Name: validationpersonsupplierpk(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validationpersonsupplierpk() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare existe integer;

BEGIN
	existe = (select count(1) from physics_person where id = NEW.person_id);
	if(existe <= 0) then
	   existe = (select count(1) from juridic_person where id = NEW.supplier_id);
	if(existe <= 0) then
	   raise exception 'Não foi encontrado o ID ou PK da Pessoa para realizar Associação';
	   end if;
	end if;

	RETURN NEW;

END
$$;


ALTER FUNCTION public.validationpersonsupplierpk() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 219 (class 1259 OID 32889)
-- Name: account_payable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_payable (
    id bigint NOT NULL,
    date_due date,
    date_payment date,
    description character varying(255) NOT NULL,
    status_account_payable character varying(255) NOT NULL,
    value_discount numeric(38,2),
    value_total numeric(38,2) NOT NULL,
    person_id bigint NOT NULL,
    supplier_id bigint NOT NULL,
    CONSTRAINT account_payable_status_account_payable_check CHECK (((status_account_payable)::text = ANY ((ARRAY['COBRANCA'::character varying, 'VENCIDA'::character varying, 'ABERTA'::character varying, 'ALUGUEL'::character varying, 'FUNCIONARIO'::character varying, 'NEGOCIADA'::character varying])::text[])))
);


ALTER TABLE public.account_payable OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 32898)
-- Name: account_receivable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_receivable (
    id bigint NOT NULL,
    date_due date NOT NULL,
    date_payment date,
    description character varying(255) NOT NULL,
    status_account_receivable character varying(255) NOT NULL,
    value_discount numeric(38,2),
    value_total numeric(38,2) NOT NULL,
    company_id bigint NOT NULL,
    person_id bigint NOT NULL,
    CONSTRAINT account_receivable_status_account_receivable_check CHECK (((status_account_receivable)::text = ANY ((ARRAY['COBRANCA'::character varying, 'VENCIDA'::character varying, 'ABERTA'::character varying, 'QUITADA'::character varying])::text[])))
);


ALTER TABLE public.account_receivable OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 32458)
-- Name: acess; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acess (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    company_id bigint NOT NULL
);


ALTER TABLE public.acess OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 32463)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id bigint NOT NULL,
    address_type character varying(255),
    cep character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    complement character varying(255),
    district character varying(255) NOT NULL,
    number character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    uf character varying(255) NOT NULL,
    company_id bigint NOT NULL,
    person_id bigint NOT NULL,
    CONSTRAINT address_address_type_check CHECK (((address_type)::text = ANY ((ARRAY['COBRANCA'::character varying, 'ENTREGA'::character varying])::text[])))
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 32472)
-- Name: buy_and_sale_online_store; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buy_and_sale_online_store (
    id bigint NOT NULL,
    billing_date integer NOT NULL,
    code_label character varying(255),
    delivery_date date NOT NULL,
    delivery_day integer NOT NULL,
    service_tracking character varying(255),
    shipping_cost numeric(38,2) NOT NULL,
    status_sale character varying(255),
    url_print_label character varying(255),
    value_discount numeric(38,2),
    value_total numeric(38,2) NOT NULL,
    billing_address_id bigint NOT NULL,
    company_id bigint NOT NULL,
    coupon_discount_id bigint,
    delivery_address_id bigint NOT NULL,
    payment_method_id bigint NOT NULL,
    person_id bigint NOT NULL,
    sale_nf_id bigint,
    CONSTRAINT buy_and_sale_online_store_status_sale_check CHECK (((status_sale)::text = ANY ((ARRAY['FINALIZADA'::character varying, 'CANCELADA'::character varying, 'ABANDONOU_CARRINHO'::character varying])::text[])))
);


ALTER TABLE public.buy_and_sale_online_store OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 32481)
-- Name: coupon_discount; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coupon_discount (
    id bigint NOT NULL,
    cod_discount character varying(255) NOT NULL,
    dt_expiration_discount date NOT NULL,
    value_percent_discount numeric(38,2),
    value_real_discount numeric(38,2),
    company_id bigint NOT NULL
);


ALTER TABLE public.coupon_discount OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 32486)
-- Name: juridic_person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.juridic_person (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    type_person character varying(255),
    company_id bigint NOT NULL,
    category character varying(255),
    cnpj character varying(255) NOT NULL,
    company_name character varying(255) NOT NULL,
    municipal_registration character varying(255),
    state_registration character varying(255) NOT NULL,
    trade_name character varying(255) NOT NULL
);


ALTER TABLE public.juridic_person OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 32494)
-- Name: payment_method; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_method (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    company_id bigint NOT NULL
);


ALTER TABLE public.payment_method OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 32499)
-- Name: physics_person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.physics_person (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    type_person character varying(255),
    company_id bigint NOT NULL,
    cpf character varying(255) NOT NULL,
    date_birth date
);


ALTER TABLE public.physics_person OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 32507)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    active boolean NOT NULL,
    alert_stock_quantity boolean,
    depth double precision NOT NULL,
    description text NOT NULL,
    height double precision NOT NULL,
    link_youtube character varying(255),
    name character varying(255) NOT NULL,
    quantity_alert_stock integer,
    quantity_click integer,
    selling_price numeric(38,2) NOT NULL,
    stock_quantity integer NOT NULL,
    unit_type character varying(255) NOT NULL,
    weight double precision NOT NULL,
    width double precision NOT NULL,
    company_id bigint NOT NULL,
    product_brand_id bigint NOT NULL,
    product_category_id bigint NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 32515)
-- Name: product_brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_brand (
    pb_id bigint NOT NULL,
    pb_descrition character varying(255) NOT NULL,
    empresa_id bigint NOT NULL
);


ALTER TABLE public.product_brand OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 32520)
-- Name: product_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_category (
    pc_id bigint NOT NULL,
    pc_descrition character varying(255) NOT NULL,
    company_id bigint NOT NULL
);


ALTER TABLE public.product_category OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 32588)
-- Name: product_category_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_category_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_category_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 32922)
-- Name: product_evaluation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_evaluation (
    id bigint NOT NULL,
    description character varying(255) NOT NULL,
    notes integer NOT NULL,
    company_id bigint NOT NULL,
    person_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.product_evaluation OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 32530)
-- Name: product_image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_image (
    id bigint NOT NULL,
    image_original text NOT NULL,
    image_thumbnail text NOT NULL,
    company_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.product_image OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 32538)
-- Name: product_item_nf; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_item_nf (
    id bigint NOT NULL,
    quantity double precision NOT NULL,
    company_id bigint NOT NULL,
    product_id bigint NOT NULL,
    purchase_nf_id bigint NOT NULL
);


ALTER TABLE public.product_item_nf OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 32945)
-- Name: purchase_nf; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase_nf (
    id bigint NOT NULL,
    date_purchase date NOT NULL,
    description_obs character varying(255),
    number_nf character varying(255) NOT NULL,
    serial_nf character varying(255) NOT NULL,
    value_discount numeric(38,2),
    value_icms numeric(38,2) NOT NULL,
    value_total numeric(38,2) NOT NULL,
    account_payable_id bigint NOT NULL,
    company_id bigint NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.purchase_nf OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 32551)
-- Name: sale_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sale_item (
    id bigint NOT NULL,
    quantity double precision NOT NULL,
    buy_and_sale_id bigint NOT NULL,
    empresa_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.sale_item OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 32556)
-- Name: sale_nf; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sale_nf (
    id bigint NOT NULL,
    key character varying(255) NOT NULL,
    number character varying(255) NOT NULL,
    pdf text NOT NULL,
    serial character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    xml text NOT NULL,
    buy_and_sale_online_store_id bigint,
    company_id bigint NOT NULL
);


ALTER TABLE public.sale_nf OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 32590)
-- Name: seq_account_payable; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_account_payable
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_account_payable OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 32592)
-- Name: seq_account_receivable; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_account_receivable
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_account_receivable OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 32594)
-- Name: seq_acess; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_acess
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_acess OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 32596)
-- Name: seq_address; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_address
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_address OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 32598)
-- Name: seq_buy_and_sale_onlie_store; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_buy_and_sale_onlie_store
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_buy_and_sale_onlie_store OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 32600)
-- Name: seq_coupon_discount; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_coupon_discount
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_coupon_discount OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 32602)
-- Name: seq_payment_method; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_payment_method
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_payment_method OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 32604)
-- Name: seq_person; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_person
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_person OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 32606)
-- Name: seq_product; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 32608)
-- Name: seq_product_brand; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_brand
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_brand OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 32610)
-- Name: seq_product_evaluation; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_evaluation
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_evaluation OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 32612)
-- Name: seq_product_image; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_image
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_image OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 32614)
-- Name: seq_product_item_nf; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_item_nf
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_item_nf OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24737)
-- Name: seq_product_nf; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_product_nf
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_nf OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 32616)
-- Name: seq_purchase_nf; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_purchase_nf
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_purchase_nf OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 32618)
-- Name: seq_sale_item; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_sale_item
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_sale_item OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 32620)
-- Name: seq_sale_nf; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_sale_nf
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_sale_nf OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 32622)
-- Name: seq_tracking_status; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_tracking_status
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tracking_status OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 24583)
-- Name: seq_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_user OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 32624)
-- Name: seq_users; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_users
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_users OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 32564)
-- Name: tracking_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tracking_status (
    id bigint NOT NULL,
    url_tracking character varying(255),
    buy_and_sale_id bigint NOT NULL,
    company_id bigint NOT NULL
);


ALTER TABLE public.tracking_status OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 32569)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    current_password_date date,
    login character varying(255),
    password character varying(255),
    person_id bigint NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 32577)
-- Name: users_acess; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_acess (
    users_id bigint NOT NULL,
    acess_id bigint NOT NULL
);


ALTER TABLE public.users_acess OWNER TO postgres;

--
-- TOC entry 2365 (class 0 OID 32889)
-- Dependencies: 219
-- Data for Name: account_payable; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2366 (class 0 OID 32898)
-- Dependencies: 220
-- Data for Name: account_receivable; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2329 (class 0 OID 32458)
-- Dependencies: 183
-- Data for Name: acess; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2330 (class 0 OID 32463)
-- Dependencies: 184
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2331 (class 0 OID 32472)
-- Dependencies: 185
-- Data for Name: buy_and_sale_online_store; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2332 (class 0 OID 32481)
-- Dependencies: 186
-- Data for Name: coupon_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2333 (class 0 OID 32486)
-- Dependencies: 187
-- Data for Name: juridic_person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.juridic_person (id, email, name, phone, type_person, company_id, category, cnpj, company_name, municipal_registration, state_registration, trade_name) VALUES (1, 'teste@teste.com', 'EMPRESA 1', '6799123333', '1', 1, '1', '1929939222', 'EMPRESA TESTE', '00000000', '0000000000', 'TESTE ');


--
-- TOC entry 2334 (class 0 OID 32494)
-- Dependencies: 188
-- Data for Name: payment_method; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2335 (class 0 OID 32499)
-- Dependencies: 189
-- Data for Name: physics_person; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2336 (class 0 OID 32507)
-- Dependencies: 190
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, active, alert_stock_quantity, depth, description, height, link_youtube, name, quantity_alert_stock, quantity_click, selling_price, stock_quantity, unit_type, weight, width, company_id, product_brand_id, product_category_id) VALUES (1, true, true, 50, 'produto teste', 30, 'teste', 'Produto Teste', 10, 3, 80.50, 14, '1', 22.199999999999999, 3, 1, 1, 1);


--
-- TOC entry 2337 (class 0 OID 32515)
-- Dependencies: 191
-- Data for Name: product_brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product_brand (pb_id, pb_descrition, empresa_id) VALUES (1, 'MARCA TESTE', 1);


--
-- TOC entry 2338 (class 0 OID 32520)
-- Dependencies: 192
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product_category (pc_id, pc_descrition, company_id) VALUES (1, 'CATEGORIA TESTE', 1);


--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 200
-- Name: product_category_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_category_seq', 1, false);


--
-- TOC entry 2367 (class 0 OID 32922)
-- Dependencies: 221
-- Data for Name: product_evaluation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2339 (class 0 OID 32530)
-- Dependencies: 193
-- Data for Name: product_image; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2340 (class 0 OID 32538)
-- Dependencies: 194
-- Data for Name: product_item_nf; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2368 (class 0 OID 32945)
-- Dependencies: 222
-- Data for Name: purchase_nf; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2341 (class 0 OID 32551)
-- Dependencies: 195
-- Data for Name: sale_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2342 (class 0 OID 32556)
-- Dependencies: 196
-- Data for Name: sale_nf; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2379 (class 0 OID 0)
-- Dependencies: 201
-- Name: seq_account_payable; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_account_payable', 1, false);


--
-- TOC entry 2380 (class 0 OID 0)
-- Dependencies: 202
-- Name: seq_account_receivable; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_account_receivable', 1, false);


--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 203
-- Name: seq_acess; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_acess', 1, false);


--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 204
-- Name: seq_address; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_address', 1, false);


--
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 205
-- Name: seq_buy_and_sale_onlie_store; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_buy_and_sale_onlie_store', 1, false);


--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 206
-- Name: seq_coupon_discount; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_coupon_discount', 1, false);


--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 207
-- Name: seq_payment_method; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_payment_method', 1, false);


--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 208
-- Name: seq_person; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_person', 1, false);


--
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 209
-- Name: seq_product; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product', 1, false);


--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 210
-- Name: seq_product_brand; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_brand', 1, false);


--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 211
-- Name: seq_product_evaluation; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_evaluation', 1, false);


--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 212
-- Name: seq_product_image; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_image', 1, false);


--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 213
-- Name: seq_product_item_nf; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_item_nf', 1, false);


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 182
-- Name: seq_product_nf; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_product_nf', 1, false);


--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 214
-- Name: seq_purchase_nf; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_purchase_nf', 1, false);


--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 215
-- Name: seq_sale_item; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_sale_item', 1, false);


--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_sale_nf; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_sale_nf', 1, false);


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_tracking_status; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_tracking_status', 1, false);


--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 181
-- Name: seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_user', 1, false);


--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_users; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_users', 1, false);


--
-- TOC entry 2343 (class 0 OID 32564)
-- Dependencies: 197
-- Data for Name: tracking_status; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2344 (class 0 OID 32569)
-- Dependencies: 198
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2345 (class 0 OID 32577)
-- Dependencies: 199
-- Data for Name: users_acess; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2159 (class 2606 OID 32897)
-- Name: account_payable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_payable
    ADD CONSTRAINT account_payable_pkey PRIMARY KEY (id);


--
-- TOC entry 2161 (class 2606 OID 32906)
-- Name: account_receivable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_receivable
    ADD CONSTRAINT account_receivable_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 32462)
-- Name: acess_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acess
    ADD CONSTRAINT acess_pkey PRIMARY KEY (id);


--
-- TOC entry 2121 (class 2606 OID 32471)
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 2123 (class 2606 OID 32480)
-- Name: buy_and_sale_online_store_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT buy_and_sale_online_store_pkey PRIMARY KEY (id);


--
-- TOC entry 2127 (class 2606 OID 32485)
-- Name: coupon_discount_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon_discount
    ADD CONSTRAINT coupon_discount_pkey PRIMARY KEY (id);


--
-- TOC entry 2129 (class 2606 OID 32493)
-- Name: juridic_person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juridic_person
    ADD CONSTRAINT juridic_person_pkey PRIMARY KEY (id);


--
-- TOC entry 2131 (class 2606 OID 32498)
-- Name: payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_pkey PRIMARY KEY (id);


--
-- TOC entry 2133 (class 2606 OID 32506)
-- Name: physics_person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.physics_person
    ADD CONSTRAINT physics_person_pkey PRIMARY KEY (id);


--
-- TOC entry 2137 (class 2606 OID 32519)
-- Name: product_brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_brand
    ADD CONSTRAINT product_brand_pkey PRIMARY KEY (pb_id);


--
-- TOC entry 2139 (class 2606 OID 32524)
-- Name: product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (pc_id);


--
-- TOC entry 2163 (class 2606 OID 32926)
-- Name: product_evaluation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_evaluation
    ADD CONSTRAINT product_evaluation_pkey PRIMARY KEY (id);


--
-- TOC entry 2141 (class 2606 OID 32537)
-- Name: product_image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT product_image_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 32542)
-- Name: product_item_nf_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_item_nf
    ADD CONSTRAINT product_item_nf_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 32514)
-- Name: product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2165 (class 2606 OID 32952)
-- Name: purchase_nf_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_nf
    ADD CONSTRAINT purchase_nf_pkey PRIMARY KEY (id);


--
-- TOC entry 2145 (class 2606 OID 32555)
-- Name: sale_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_item
    ADD CONSTRAINT sale_item_pkey PRIMARY KEY (id);


--
-- TOC entry 2147 (class 2606 OID 32563)
-- Name: sale_nf_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_nf
    ADD CONSTRAINT sale_nf_pkey PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 32568)
-- Name: tracking_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracking_status
    ADD CONSTRAINT tracking_status_pkey PRIMARY KEY (id);


--
-- TOC entry 2149 (class 2606 OID 32583)
-- Name: ukcjnih8tbdcuxrl2ipfy6qeuci; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_nf
    ADD CONSTRAINT ukcjnih8tbdcuxrl2ipfy6qeuci UNIQUE (buy_and_sale_online_store_id);


--
-- TOC entry 2125 (class 2606 OID 32581)
-- Name: ukdlcif1as5j957pgo7a8nfms8l; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT ukdlcif1as5j957pgo7a8nfms8l UNIQUE (sale_nf_id);


--
-- TOC entry 2155 (class 2606 OID 32587)
-- Name: ukh3208257bc3gr03vaucxt2dtp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_acess
    ADD CONSTRAINT ukh3208257bc3gr03vaucxt2dtp UNIQUE (acess_id);


--
-- TOC entry 2157 (class 2606 OID 32585)
-- Name: unique_acess_users; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_acess
    ADD CONSTRAINT unique_acess_users UNIQUE (users_id, acess_id);


--
-- TOC entry 2153 (class 2606 OID 32576)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2209 (class 2620 OID 32937)
-- Name: validationinsertpk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationinsertpk BEFORE INSERT ON public.account_receivable FOR EACH ROW EXECUTE PROCEDURE public.validationpersonsupplierpk();


--
-- TOC entry 2203 (class 2620 OID 32939)
-- Name: validationinsertpk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationinsertpk BEFORE INSERT ON public.address FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2205 (class 2620 OID 32941)
-- Name: validationinsertpk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationinsertpk BEFORE INSERT ON public.buy_and_sale_online_store FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2211 (class 2620 OID 32968)
-- Name: validationinsertpk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationinsertpk BEFORE INSERT ON public.purchase_nf FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2207 (class 2620 OID 32971)
-- Name: validationinsertpk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationinsertpk BEFORE INSERT ON public.users FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2210 (class 2620 OID 32938)
-- Name: validationupdatepk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationupdatepk BEFORE UPDATE ON public.account_receivable FOR EACH ROW EXECUTE PROCEDURE public.validationpersonsupplierpk();


--
-- TOC entry 2204 (class 2620 OID 32940)
-- Name: validationupdatepk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationupdatepk BEFORE UPDATE ON public.address FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2206 (class 2620 OID 32942)
-- Name: validationupdatepk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationupdatepk BEFORE UPDATE ON public.buy_and_sale_online_store FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2212 (class 2620 OID 32969)
-- Name: validationupdatepk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationupdatepk BEFORE UPDATE ON public.purchase_nf FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2208 (class 2620 OID 32972)
-- Name: validationupdatepk; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validationupdatepk BEFORE UPDATE ON public.users FOR EACH ROW EXECUTE PROCEDURE public.validationpersonpk();


--
-- TOC entry 2201 (class 2606 OID 32958)
-- Name: account_payable_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_nf
    ADD CONSTRAINT account_payable_fk FOREIGN KEY (account_payable_id) REFERENCES public.account_payable(id);


--
-- TOC entry 2195 (class 2606 OID 32791)
-- Name: acess_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_acess
    ADD CONSTRAINT acess_fk FOREIGN KEY (acess_id) REFERENCES public.acess(id);


--
-- TOC entry 2168 (class 2606 OID 32641)
-- Name: billing_address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT billing_address_fk FOREIGN KEY (billing_address_id) REFERENCES public.address(id);


--
-- TOC entry 2188 (class 2606 OID 32756)
-- Name: buy_and_sale_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_item
    ADD CONSTRAINT buy_and_sale_fk FOREIGN KEY (buy_and_sale_id) REFERENCES public.buy_and_sale_online_store(id);


--
-- TOC entry 2193 (class 2606 OID 32781)
-- Name: buy_and_sale_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracking_status
    ADD CONSTRAINT buy_and_sale_fk FOREIGN KEY (buy_and_sale_id) REFERENCES public.buy_and_sale_online_store(id);


--
-- TOC entry 2191 (class 2606 OID 32771)
-- Name: buy_and_sale_online_store_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_nf
    ADD CONSTRAINT buy_and_sale_online_store_fk FOREIGN KEY (buy_and_sale_online_store_id) REFERENCES public.buy_and_sale_online_store(id);


--
-- TOC entry 2166 (class 2606 OID 32631)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acess
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2167 (class 2606 OID 32636)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2169 (class 2606 OID 32646)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2174 (class 2606 OID 32671)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupon_discount
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2176 (class 2606 OID 32681)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2178 (class 2606 OID 32691)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2182 (class 2606 OID 32711)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2183 (class 2606 OID 32721)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2185 (class 2606 OID 32731)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_item_nf
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2192 (class 2606 OID 32776)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_nf
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2194 (class 2606 OID 32786)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracking_status
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2198 (class 2606 OID 32912)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_receivable
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2199 (class 2606 OID 32927)
-- Name: company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_evaluation
    ADD CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2202 (class 2606 OID 32963)
-- Name: company_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_nf
    ADD CONSTRAINT company_id_fk FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2170 (class 2606 OID 32651)
-- Name: coupon_discount_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT coupon_discount_fk FOREIGN KEY (coupon_discount_id) REFERENCES public.coupon_discount(id);


--
-- TOC entry 2171 (class 2606 OID 32656)
-- Name: delivery_address_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT delivery_address_fk FOREIGN KEY (delivery_address_id) REFERENCES public.address(id);


--
-- TOC entry 2181 (class 2606 OID 32706)
-- Name: empresa_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_brand
    ADD CONSTRAINT empresa_id_fk FOREIGN KEY (empresa_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2189 (class 2606 OID 32761)
-- Name: empresa_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_item
    ADD CONSTRAINT empresa_id_fk FOREIGN KEY (empresa_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2177 (class 2606 OID 32686)
-- Name: fkl7aru6phqus9d8skho33hc5ag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.physics_person
    ADD CONSTRAINT fkl7aru6phqus9d8skho33hc5ag FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2175 (class 2606 OID 32676)
-- Name: fklxkbma0rl8bc38bnv7rchau3i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juridic_person
    ADD CONSTRAINT fklxkbma0rl8bc38bnv7rchau3i FOREIGN KEY (company_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2172 (class 2606 OID 32661)
-- Name: payment_method_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT payment_method_fk FOREIGN KEY (payment_method_id) REFERENCES public.payment_method(id);


--
-- TOC entry 2179 (class 2606 OID 32696)
-- Name: product_brand_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_brand_fk FOREIGN KEY (product_brand_id) REFERENCES public.product_brand(pb_id);


--
-- TOC entry 2180 (class 2606 OID 32701)
-- Name: product_category_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_category_fk FOREIGN KEY (product_category_id) REFERENCES public.product_category(pc_id);


--
-- TOC entry 2184 (class 2606 OID 32726)
-- Name: product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2186 (class 2606 OID 32736)
-- Name: product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_item_nf
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2190 (class 2606 OID 32766)
-- Name: product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_item
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2200 (class 2606 OID 32932)
-- Name: product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_evaluation
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 2187 (class 2606 OID 32953)
-- Name: purchase_nf_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_item_nf
    ADD CONSTRAINT purchase_nf_fk FOREIGN KEY (purchase_nf_id) REFERENCES public.purchase_nf(id);


--
-- TOC entry 2173 (class 2606 OID 32666)
-- Name: sale_nf_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buy_and_sale_online_store
    ADD CONSTRAINT sale_nf_fk FOREIGN KEY (sale_nf_id) REFERENCES public.sale_nf(id);


--
-- TOC entry 2197 (class 2606 OID 32907)
-- Name: supplier_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_payable
    ADD CONSTRAINT supplier_fk FOREIGN KEY (supplier_id) REFERENCES public.juridic_person(id);


--
-- TOC entry 2196 (class 2606 OID 32796)
-- Name: users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_acess
    ADD CONSTRAINT users_fk FOREIGN KEY (users_id) REFERENCES public.users(id);


--
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-01-27 13:40:38

--
-- PostgreSQL database dump complete
--

