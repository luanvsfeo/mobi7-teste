package com.teste.mobi7.model.extensions

import com.teste.mobi7.dto.PontoDeInteresseDto
import com.teste.mobi7.model.PontoDeInteresse

fun PontoDeInteresseDto.converterParaModel(): PontoDeInteresse {
    return PontoDeInteresse(
        null,
        this.nome,
        this.raioEmMetros,
        this.latitude,
        this.longitude
    )
}

fun PontoDeInteresse.converterParaDto(): PontoDeInteresseDto {
    return PontoDeInteresseDto(
        this.nome,
        this.raioEmMetros,
        this.latitude,
        this.longitude
    )
}