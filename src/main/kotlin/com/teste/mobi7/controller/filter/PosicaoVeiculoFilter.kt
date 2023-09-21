package com.teste.mobi7.controller.filter

import java.time.LocalDate

class PosicaoVeiculoFilter(
	var placaVeiculo: String?,
	var dataPosicao: LocalDate?
) {


	fun estaApenasComPlacaVeiculoPreenchido(): Boolean {
		return this.placaVeiculo != null && this.dataPosicao == null
	}

	fun estaApenasComDataPosicaoPreenchido(): Boolean {
		return this.placaVeiculo == null && this.dataPosicao != null
	}

	fun estaComDataPosicaoJuntoComPlacaVeiculo(): Boolean {
		return this.placaVeiculo != null && this.dataPosicao != null
	}

	fun estaVazio(): Boolean {
		return this.placaVeiculo == null && this.dataPosicao == null
	}

}