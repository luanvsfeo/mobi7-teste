package com.teste.mobi7.controller

import com.teste.mobi7.dto.PontoDeInteresseDto
import com.teste.mobi7.service.PontoDeInteresseService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pontoDeInteresse")
class PontoDeInteresseController(
    val pontoDeInteresseService: PontoDeInteresseService
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criarNovoPontoDeInteresse(@RequestBody pontoDeInteresseDto: PontoDeInteresseDto): PontoDeInteresseDto {
        log.info("m=criarNovoPontoDeInteresse; step=start")
        val response = pontoDeInteresseService.criar(pontoDeInteresseDto)
        log.info("m=criarNovoPontoDeInteresse; step=finished; result=${response}")
        return response
    }
}