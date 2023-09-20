-- POSTGRES
CREATE SEQUENCE public.ponto_interesse_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.posicao_veiculo_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.posicao_veiculo (
	id int4 NOT NULL,
	data_posicao timestamp(6) NULL,
	latitude float8 NOT NULL,
	longitude float8 NOT NULL,
	placa_veiculo varchar(255) NOT NULL,
	veiculo_ligado bool NOT NULL,
	velocidade_veiculo int4 NOT NULL,
	CONSTRAINT posicao_veiculo_pkey PRIMARY KEY (id)
);


CREATE TABLE public.ponto_de_interesse (
	id int4 NOT NULL,
	latitude float8 NOT NULL,
	longitude float8 NOT NULL,
	nome varchar(255) NULL,
	raio_em_metros int4 NOT NULL,
	CONSTRAINT ponto_de_interesse_pkey PRIMARY KEY (id)
);

ALTER TABLE public.ponto_de_interesse ALTER COLUMN id SET DEFAULT NEXTVAL('ponto_interesse_id_seq');

ALTER TABLE public.posicao_veiculo ALTER COLUMN id SET DEFAULT NEXTVAL('posicao_veiculo_id_seq');


