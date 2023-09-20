-- H2 DATABASE

INSERT INTO posicao_veiculo(
	PLACA_VEICULO ,
	DATA_POSICAO  ,
	VELOCIDADE_VEICULO,
	LONGITUDE,
	LATITUDE,
	VEICULO_LIGADO
)
SELECT
	placa,
	data_posicao,
	velocidade,
	longitude,
	latitude,
	ignicao
FROM CSVREAD('posicoes.csv', NULL, 'charset=UTF-8');


INSERT INTO ponto_de_interesse(
		nome,
		raio,
		latitude,
		longitude
)
SELECT
		nome,
		raio,
		latitude,
		longitude
FROM CSVREAD('base_pois_def.csv', NULL, 'charset=UTF-8');
