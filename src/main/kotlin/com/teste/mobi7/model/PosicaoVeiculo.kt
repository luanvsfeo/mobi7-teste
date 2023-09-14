package com.teste.mobi7.model

import com.teste.mobi7.dto.PosicaoVeiculoDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime


@Entity
class PosicaoVeiculo(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,

    var placaVeiculo: String,

    var dataPosicao: LocalDateTime,

    var velocidadeVeiculo: Int,

    var latitude: Double,

    var longitude: Double,

    var veiculoLigado: Boolean
) {

    fun converterDto(): PosicaoVeiculoDto {
        return PosicaoVeiculoDto(
            this.placaVeiculo,
            this.dataPosicao,
            this.velocidadeVeiculo,
            this.latitude,
            this.longitude,
            this.veiculoLigado
        )
    }
}

/*
TESTE001,Wed Dec 19 2018 15:07:19 GMT-0200 (Hora oficial do Brasil),10,-
51.549662,-25.5244493,true
 */