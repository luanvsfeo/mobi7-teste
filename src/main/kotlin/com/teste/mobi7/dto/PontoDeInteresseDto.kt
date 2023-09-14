package com.teste.mobi7.dto

import com.teste.mobi7.model.PontoDeInteresse

// colocar validacao de numero


class PontoDeInteresseDto(

    var nome: String,

    var raioEmMetros: Int,

    var latitude: Double,

    var longitude: Double
) {
    fun converterParaModel(): PontoDeInteresse {
        return PontoDeInteresse(
            null,
            this.nome,
            this.raioEmMetros,
            this.latitude,
            this.longitude
        )
    }
}