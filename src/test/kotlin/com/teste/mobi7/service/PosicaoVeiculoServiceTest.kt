package com.teste.mobi7.service

import com.teste.mobi7.dto.PosicaoVeiculoDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@ActiveProfiles(profiles = ["test"])
@SpringBootTest
class PosicaoVeiculoServiceTest {

	@Autowired
	private lateinit var posicaoVeiculoService: PosicaoVeiculoService

	@Test
	fun criarUmPosicaoVeiculo() {

		var posivaoVeiculo = PosicaoVeiculoDto("TESTE0012", LocalDateTime.now(), 0,-25.36496636999715, -51.46980205405271,false)
		posicaoVeiculoService.criar(posivaoVeiculo)
		val resposta = posicaoVeiculoService.buscarPorFiltro(null).get(0)

		Assertions.assertEquals(resposta.placaVeiculo, posivaoVeiculo.placaVeiculo)
	}
}