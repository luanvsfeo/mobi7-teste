package com.teste.mobi7.controller

import com.teste.mobi7.dto.PosicaoVeiculoDto
import com.teste.mobi7.service.PosicaoVeiculoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posicaoVeiculo")
class PosicaoVeiculoController(
    val posicaoVeiculoService: PosicaoVeiculoService
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criarNovaPosicaoVeiculo(posicaoVeiculoDto: PosicaoVeiculoDto): PosicaoVeiculoDto {
        log.info("m=criarNovaPosicaoVeiculo; step=start")
        val response = posicaoVeiculoService.criar(posicaoVeiculoDto)
        log.info("m=criarNovaPosicaoVeiculo; step=finished; result=${response}")
        return response
    }
}