package com.teste.mobi7.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/relatorio")
class RelatorioController {


    @GetMapping
    fun retornarRelatorioDeVeiculosRelacionadoAoPontoDeInteresse() {

    }
}