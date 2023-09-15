package com.teste.mobi7.dto

import java.time.LocalDateTime

data class PosicaoVeiculoDto(

    var placaVeiculo: String,

    var dataPosicao: LocalDateTime,

    var velocidadeVeiculo: Int,

    var latitude: Double,

    var longitude: Double,

    var veiculoLigado: Boolean
)