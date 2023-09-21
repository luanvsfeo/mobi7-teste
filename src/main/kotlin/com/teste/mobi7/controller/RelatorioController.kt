package com.teste.mobi7.controller

import com.teste.mobi7.controller.filter.PosicaoVeiculoFilter
import com.teste.mobi7.dto.PontoInteresseTempoDto
import com.teste.mobi7.service.RelatorioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/relatorio")
class RelatorioController(
	val relatorioService: RelatorioService
) {
	private val log = LoggerFactory.getLogger(this::class.java)

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	fun retornarRelatorioDeVeiculosRelacionadoAoPontoDeInteresse(posicaoVeiculoFilter: PosicaoVeiculoFilter?): MutableMap<String, List<PontoInteresseTempoDto>> {
		log.info("m=retornarRelatorioDeVeiculosRelacionadoAoPontoDeInteresse; step=start")
		val resposta = relatorioService.buscarPorFiltro(posicaoVeiculoFilter)
		log.info("m=retornarRelatorioDeVeiculosRelacionadoAoPontoDeInteresse; step=finished; result=${resposta}")
		return resposta
	}
}