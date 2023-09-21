package com.teste.mobi7.service

import com.teste.mobi7.controller.filter.PosicaoVeiculoFilter
import com.teste.mobi7.dto.PosicaoVeiculoDto
import com.teste.mobi7.model.PosicaoVeiculo
import com.teste.mobi7.model.extensions.converterDto
import com.teste.mobi7.model.extensions.converterParaModel
import com.teste.mobi7.repository.PosicaoVeiculoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PosicaoVeiculoService(
	val posicaoVeiculoRepository: PosicaoVeiculoRepository
) {
	private val log = LoggerFactory.getLogger(this::class.java)

	fun criar(posicaoVeiculoDto: PosicaoVeiculoDto): PosicaoVeiculoDto {
		log.info("m=criar; step=start; posicaoVeiculoDto=${posicaoVeiculoDto}")
		val posicaoVeiculo = posicaoVeiculoDto.converterParaModel();
		val posicaoVeiculoCriado = posicaoVeiculoRepository.save(posicaoVeiculo)
		log.info("m=criar; step=finished; posicaoVeiculoCriado=${posicaoVeiculoCriado}")
		return posicaoVeiculoCriado.converterDto()
	}


	fun buscarPorFiltro(posicaoVeiculoFilter: PosicaoVeiculoFilter?): MutableList<PosicaoVeiculo> {
		// todo - colocar log e testar se o filtro esta realmente funcionando

		return if (posicaoVeiculoFilter?.placaVeiculo == null && posicaoVeiculoFilter?.dataPosicao == null) {
			posicaoVeiculoRepository.findAll()
		} else (if (posicaoVeiculoFilter.placaVeiculo != null && posicaoVeiculoFilter.dataPosicao == null) {
			posicaoVeiculoRepository.findAllByPlacaVeiculo(posicaoVeiculoFilter.placaVeiculo!!).toMutableList()

		} else if (posicaoVeiculoFilter.placaVeiculo == null && posicaoVeiculoFilter.dataPosicao != null) {
			posicaoVeiculoRepository.findAllByDataPosicao(posicaoVeiculoFilter.dataPosicao!!).toMutableList()

		} else if (posicaoVeiculoFilter.placaVeiculo != null && posicaoVeiculoFilter.dataPosicao != null) {
			posicaoVeiculoRepository.findAllByPlacaVeiculoAndDataPosicao(
				posicaoVeiculoFilter.placaVeiculo!!,
				posicaoVeiculoFilter.dataPosicao!!
			).toMutableList()
		} else {
			listOf<PosicaoVeiculo>()
		}).toMutableList()
	}
}