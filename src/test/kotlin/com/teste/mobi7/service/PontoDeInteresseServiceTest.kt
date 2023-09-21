package com.teste.mobi7.service

import com.teste.mobi7.dto.PontoDeInteresseDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles(profiles = ["test"])
@SpringBootTest
class PontoDeInteresseServiceTest {

	@Autowired
	private lateinit var pontoDeInteresseService: PontoDeInteresseService

	@Test
	fun criarUmPontoDeInteresse() {

		val poi = PontoDeInteresseDto("cafeteria", 350, -25.36496636999715, -51.46980205405271)
		pontoDeInteresseService.criar(poi)
		val resposta = pontoDeInteresseService.buscarTodos().get(0)

		Assertions.assertEquals(resposta.nome, poi.nome)
	}
}