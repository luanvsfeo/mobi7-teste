package com.teste.mobi7.service

import com.teste.mobi7.model.PontoDeInteresse
import com.teste.mobi7.model.PosicaoVeiculo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class RelatorioServiceTest {
	// todo - criar teste de integracao com o banco de dados h2 (teste de criacao no banco)

	private val posicaoVeiculoService: PosicaoVeiculoService = mockk()
	private val pontoDeInteresseService: PontoDeInteresseService = mockk()
	private var relatorioService: RelatorioService

	init {
		relatorioService = RelatorioService(posicaoVeiculoService, pontoDeInteresseService)

		every { posicaoVeiculoService.buscarPorFiltro(any()) } returns gerarListPosicaoVeiculo();
		every { pontoDeInteresseService.buscarTodos() } returns gerarListaPontoInteresse()
	}

	@Test
	fun ficouDentroDoPontoDeInteressePor35MinutosComDuasPosicoes() {
		val resposta = relatorioService.buscarPorFiltro(null)

		resposta.get("teste")?.get(0)?.let { assertEquals(35L, it.tempoEmMinutos) }
	}

	@Test
	fun naoPossuiPosicaoDentro() {
		every { posicaoVeiculoService.buscarPorFiltro(any()) } returns gerarListPosicaoVeiculoSemPontoDentro();

		val resposta = relatorioService.buscarPorFiltro(null)
		assertEquals(true, resposta.isNullOrEmpty())
	}


	@Test
	fun ficouDentroDoPontoDeInteressePor130MinutosComTodasPosicoes() {
		every { posicaoVeiculoService.buscarPorFiltro(any()) } returns gerarListPosicaoVeiculoComTodosPontoDentro();

		val resposta = relatorioService.buscarPorFiltro(null)
		resposta.get("teste")?.get(0)?.let { assertEquals(130L, it.tempoEmMinutos) }
	}


	@Test
	fun ficouDentroDoPontoDeInteressComTodasPosicoesVeiculosDiferentes() {
		every { posicaoVeiculoService.buscarPorFiltro(any()) } returns gerarListPosicaoVeiculoComTodosPontoDentroVeiculosDiferentes();

		val resposta = relatorioService.buscarPorFiltro(null)

		assertEquals(10L,resposta.getOrDefault("relampago", listOf())[0].tempoEmMinutos)
		assertEquals(15L,resposta.getOrDefault("mcqueen", listOf())[0].tempoEmMinutos)
	}


	private fun gerarListPosicaoVeiculo(): MutableList<PosicaoVeiculo> {
		var list = mutableListOf<PosicaoVeiculo>()

		list.add(
			PosicaoVeiculo(
				2,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 20)),
				0,
				-25.364617,
				-51.469893,
				false
			)
		) // dentro
		list.add(
			PosicaoVeiculo(
				3,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)),
				0,
				-25.364966,
				-51.479802,
				false
			)
		) // fora
		list.add(
			PosicaoVeiculo(
				4,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 40)),
				0,
				-25.366574,
				-51.4716927,
				false
			)
		) // fora
		list.add(
			PosicaoVeiculo(
				5,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 45)),
				0,
				-25.3651379,
				-51.4697745,
				false
			)
		) // dentro
		return list
	}

	private fun gerarListPosicaoVeiculoSemPontoDentro(): MutableList<PosicaoVeiculo> {
		var list = mutableListOf<PosicaoVeiculo>()

		list.add(
			PosicaoVeiculo(
				2,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 20)),
				0,
				-25.364617,
				-52.469893,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				3,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)),
				0,
				-25.364966,
				-53.479802,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				4,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 40)),
				0,
				-25.366574,
				-54.4716927,
				false
			)
		)
		return list
	}

	private fun gerarListPosicaoVeiculoComTodosPontoDentro(): MutableList<PosicaoVeiculo> {
		var list = mutableListOf<PosicaoVeiculo>()

		list.add(
			PosicaoVeiculo(
				2,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 20)),
				0,
				-25.364617,
				-51.469893,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				3,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)),
				0,
				-25.364617,
				-51.469893,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				4,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 40)),
				0,
				-25.3651379,
				-51.4697745,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				5,
				"teste",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 45)),
				0,
				-25.3651379,
				-51.4697745,
				false
			)
		)
		return list
	}

	private fun gerarListPosicaoVeiculoComTodosPontoDentroVeiculosDiferentes(): MutableList<PosicaoVeiculo> {
		var list = mutableListOf<PosicaoVeiculo>()

		list.add(
			PosicaoVeiculo(
				2,
				"relampago",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 20)),
				0,
				-25.364617,
				-51.469893,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				3,
				"relampago",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)),
				0,
				-25.364617,
				-51.469893,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				4,
				"mcqueen",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 40)),
				0,
				-25.3651379,
				-51.4697745,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				5,
				"mcqueen",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 45)),
				0,
				-25.3651379,
				-51.4697745,
				false
			)
		)
		list.add(
			PosicaoVeiculo(
				5,
				"mcqueen",
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 55)),
				0,
				-25.3651379,
				-51.4697745,
				false
			)
		)
		return list
	}


	private fun gerarListaPontoInteresse(): MutableList<PontoDeInteresse> {
		var list = mutableListOf<PontoDeInteresse>()
		list.add(PontoDeInteresse(1, "padaria do ze", 100, -25.36496636999715, -51.46980205405271))
		return list
	}
}