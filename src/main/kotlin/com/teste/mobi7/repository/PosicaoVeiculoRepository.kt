package com.teste.mobi7.repository

import com.teste.mobi7.model.PosicaoVeiculo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface PosicaoVeiculoRepository : JpaRepository<PosicaoVeiculo, Int> {
	fun findAllByPlacaVeiculo(placaVeiculo: String): MutableList<PosicaoVeiculo>

	@Query(
		value = "select * from posicao_veiculo where date(data_posicao) = :data_posicao ",
		nativeQuery = true
	)
	fun findAllWithDataPosicao(@Param("data_posicao") dataPosicao: LocalDate): MutableList<PosicaoVeiculo>

	@Query(
		value = "select * from posicao_veiculo where date(data_posicao) = :data_posicao and placa_veiculo = :placa_veiculo ",
		nativeQuery = true
	)
	fun findAllByPlacaVeiculoAndDataPosicao(
		@Param("placa_veiculo") placaVeiculo: String,
		@Param("data_posicao") dataPosicao: LocalDate
	): MutableList<PosicaoVeiculo>
}

