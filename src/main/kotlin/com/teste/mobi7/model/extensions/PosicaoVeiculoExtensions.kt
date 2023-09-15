package com.teste.mobi7.model.extensions

import com.teste.mobi7.dto.PosicaoVeiculoDto
import com.teste.mobi7.model.PosicaoVeiculo

fun PosicaoVeiculo.converterDto(): PosicaoVeiculoDto {
    return PosicaoVeiculoDto(
        this.placaVeiculo,
        this.dataPosicao,
        this.velocidadeVeiculo,
        this.latitude,
        this.longitude,
        this.veiculoLigado
    )
}

fun PosicaoVeiculoDto.converterParaModel(): PosicaoVeiculo {
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