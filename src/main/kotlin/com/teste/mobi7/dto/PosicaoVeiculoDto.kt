package com.teste.mobi7.dto

import com.teste.mobi7.model.PosicaoVeiculo
import java.time.LocalDateTime

// todo - colocar validacao de numero

class PosicaoVeiculoDto(

    var placaVeiculo: String,

    var dataPosicao: LocalDateTime,

    var velocidadeVeiculo: Int,

    var latitude: Double,

    var longitude: Double,

    var veiculoLigado: Boolean
) {
    fun converterParaModel(): PosicaoVeiculo {
        return PosicaoVeiculo(
            null,
            this.placaVeiculo,
            this.dataPosicao,
            this.velocidadeVeiculo,
            this.latitude,
            this.longitude,
            this.veiculoLigado
        )
    }
}