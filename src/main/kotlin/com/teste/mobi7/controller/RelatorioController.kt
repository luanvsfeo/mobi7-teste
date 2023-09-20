package com.teste.mobi7.controller

import com.teste.mobi7.controller.filter.PosicaoVeiculoFilter
import com.teste.mobi7.service.RelatorioService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/relatorio")
class RelatorioController (
    val relatorioService: RelatorioService
){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun retornarRelatorioDeVeiculosRelacionadoAoPontoDeInteresse(posicaoVeiculoFilter : PosicaoVeiculoFilter) {
        relatorioService.buscarPorFiltro(posicaoVeiculoFilter)
    }
}