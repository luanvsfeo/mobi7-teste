package com.teste.mobi7.service

import com.teste.mobi7.model.PontoDeInteresse
import com.teste.mobi7.model.PosicaoVeiculo
import com.teste.mobi7.repository.PontoDeInteresseRepository
import com.teste.mobi7.repository.PosicaoVeiculoRepository
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.util.GeometricShapeFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.cos


@Service
class RelatorioService(
    val posicaoVeiculoRepository: PosicaoVeiculoRepository,
    val pontoDeInteresseRepository: PontoDeInteresseRepository,
) {

    // todo - depois trocar para as services


    fun buscarPorFiltro() {

        // criar map da relacao placaCarro X (local e tempo)


        var ponto = PontoDeInteresse(1, "padaria do ze", 100, -25.36496636999715, -51.46980205405271)
        var posicaoVeiculoLista = generateList()
        posicaoVeiculoLista.sortBy { it.dataPosicao }

        for (posicaoVeiculo in posicaoVeiculoLista) {
            // pegar o ponto anterior
            //nao fazer essa logica no primeiro ponto apenas se tiver apenas um ponto na lista

            if (estaDentroDoCirculo(converterEmCirculo(ponto), converterEmPonto(posicaoVeiculo))) {
                calcularTempoEntreDuasDatasEmMinutos(LocalDateTime.now(), posicaoVeiculo.dataPosicao)
            }

        }
        // val listaPosicoes = posicaoVeiculoRepository.findAll()
        // val listaPoi = pontoDeInteresseRepository.findAll()


        // buscar as posicoes por data e/ou placa de veiculo

        /*
            busca as posicoes
            separa por veiculo ou separa por poi
            faz o calculo do poi
            retorna a lista de poi por veiculo
         */
    }

    private fun calcularTempoEntreDuasDatasEmMinutos(
        dataPontoAtual: LocalDateTime,
        dataPontoAnterior: LocalDateTime
    ): Long {
        return ChronoUnit.MINUTES.between(dataPontoAtual, dataPontoAnterior)
    }

    private fun estaDentroDoCirculo(
        circulo: Polygon,
        ponto: Geometry
    ): Boolean {
        return circulo.contains(ponto)
    }

    private fun converterEmPonto(latitude: Double, longitude: Double): Geometry {
        val geometryFactory = GeometryFactory()
        return geometryFactory.createPoint(Coordinate(latitude, longitude))
    }

    private fun converterEmPonto(posicaoVeiculo: PosicaoVeiculo): Geometry {
        return converterEmPonto(posicaoVeiculo.latitude, posicaoVeiculo.longitude)
    }

    private fun converterEmCirculo(pontoDeInteresse: PontoDeInteresse): Polygon {
        return converterEmCirculo(
            pontoDeInteresse.latitude,
            pontoDeInteresse.longitude,
            pontoDeInteresse.raioEmMetros.toDouble()
        )
    }


    private fun converterEmCirculo(latitude: Double, longitude: Double, raioEmMetros: Double): Polygon {
        // -25.36496636999715, -51.46980205405271
        val shapeFactory = GeometricShapeFactory()
        shapeFactory.setNumPoints(64)

        shapeFactory.setCentre(Coordinate(latitude, longitude))
        // Length in meters of 1° of latitude = always 111.32 km
        // Length in meters of 1° of latitude = always 111.32 km
        shapeFactory.setWidth(raioEmMetros / 111320.0)
        // Length in meters of 1° of longitude = 40075 km * cos( latitude ) / 360
        // Length in meters of 1° of longitude = 40075 km * cos( latitude ) / 360
        shapeFactory.setHeight(raioEmMetros / (40075000 * cos(Math.toRadians(latitude)) / 360))

        return shapeFactory.createEllipse()
    }


    private fun generateList(): MutableList<PosicaoVeiculo> {
        var list = mutableListOf<PosicaoVeiculo>()

        list.add(
            PosicaoVeiculo(
                2,
                "teste",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 20)),
                0,
                -25.364617,
                -51.469893,
                false
            )
        ) // dentro
        list.add(
            PosicaoVeiculo(
                3,
                "teste",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)),
                0,
                -25.364966,
                -51.479802,
                false
            )
        ) // fora
        list.add(
            PosicaoVeiculo(
                4,
                "teste",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 40)),
                0,
                -25.366574,
                -51.4716927,
                false
            )
        ) // fora
        list.add(
            PosicaoVeiculo(
                5,
                "teste",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50)),
                0,
                -25.3651379,
                -51.4697745,
                false
            )
        ) // dentro
        return list
    }
}
