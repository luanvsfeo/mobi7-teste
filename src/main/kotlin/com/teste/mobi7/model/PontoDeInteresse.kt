package com.teste.mobi7.model

import com.teste.mobi7.dto.PontoDeInteresseDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class PontoDeInteresse(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,

    var nome: String,

    var raioEmMetros: Int,

    var latitude: Double,

    var longitude: Double
) {
    fun converterParaDto(): PontoDeInteresseDto {
        return PontoDeInteresseDto(
            this.nome,
            this.raioEmMetros,
            this.latitude,
            this.longitude
        )
    }
}


/*
PONTO 24,350,-25.36496636999715,-51.46980205405271
 */
