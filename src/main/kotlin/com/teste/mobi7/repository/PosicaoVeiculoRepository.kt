package com.teste.mobi7.repository

import com.teste.mobi7.model.PosicaoVeiculo
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface PosicaoVeiculoRepository : JpaRepository<PosicaoVeiculo, Int>{
	// todo - provavelmente o ultimo metodo esta errado
	fun findAllByPlacaVeiculo(placaVeiculo: String) : MutableList<PosicaoVeiculo>

	fun findAllByDataPosicao(dataPosicao: LocalDateTime) : MutableList<PosicaoVeiculo>

	fun findAllByPlacaVeiculoAndDataPosicao(placaVeiculo: String, dataPosicao: LocalDateTime) : MutableList<PosicaoVeiculo>
}

