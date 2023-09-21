package com.teste.mobi7.service

import com.teste.mobi7.controller.filter.PosicaoVeiculoFilter
import com.teste.mobi7.dto.PontoInteresseTempoDto
import com.teste.mobi7.model.PontoDeInteresse
import com.teste.mobi7.model.PosicaoVeiculo
import org.apache.commons.collections4.SetValuedMap
import org.apache.commons.collections4.multimap.HashSetValuedHashMap
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.util.GeometricShapeFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.cos


@Service
class RelatorioService(
	val posicaoVeiculoService: PosicaoVeiculoService,
	val pontoDeInteresseService: PontoDeInteresseService,
) {

	private val log = LoggerFactory.getLogger(this::class.java)

	//todo colocar log

	fun buscarPorFiltro(posicaoVeiculoFilter: PosicaoVeiculoFilter?): MutableMap<String, List<PontoInteresseTempoDto>> {

		var pontoDeInteresseLista = pontoDeInteresseService.buscarTodos()

		var posicaoVeiculoLista = posicaoVeiculoService.buscarPorFiltro(posicaoVeiculoFilter)

		val mapPlacaVeiculoPosicaoVeiculo = separarPorPlacaDeVeiculo(posicaoVeiculoLista)

		val mapPlacaVeiculoPontoInteresseTempo = construirMapPlacaVeiculoPontoInteresseTempo(mapPlacaVeiculoPosicaoVeiculo, pontoDeInteresseLista)

		val mapPlacaVeiculoLocalTempoDto = converterEmDto(mapPlacaVeiculoPontoInteresseTempo)

		return mapPlacaVeiculoLocalTempoDto
	}

	private fun construirMapPlacaVeiculoPontoInteresseTempo(
		mapPlacaVeiculoPosicaoVeiculo: SetValuedMap<String, PosicaoVeiculo>,
		pontoInteresseLista: MutableList<PontoDeInteresse>
	): MutableMap<String, MutableMap<String, Long>> {
		var mapTempoPlacaLocal: MutableMap<String, MutableMap<String, Long>> = HashMap()

		for (placaVeiculoString in mapPlacaVeiculoPosicaoVeiculo.keySet()) {

			var listPosicaoVeiculo = mapPlacaVeiculoPosicaoVeiculo.get(placaVeiculoString).toList()
			listPosicaoVeiculo = listPosicaoVeiculo.sortedBy { it.dataPosicao }

			var i = 0

			for (posicaoVeiculo in listPosicaoVeiculo) {
				if (listPosicaoVeiculo.size > 1 && i == 0) {
					i += 1;
					continue
				} else if (i >= 1) {
					pontoInteresseLista.forEach {

						val posicaoAnterior = listPosicaoVeiculo.get(i - 1)

						if (estaoDentroDoCirculo(it, listOf(posicaoVeiculo, posicaoAnterior))) {
							var tempoDentroDoPOIEmMinutos = calcularTempoEntreDuasDatasEmMinutos(
								posicaoAnterior.dataPosicao,
								posicaoVeiculo.dataPosicao
							)

							if (mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo]?.containsKey(it.nome) == true) {
								var tempoDentroDoPOIEmMinutosIncrementadoComValoresAnteriores =
									mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo]?.get(it.nome)
								tempoDentroDoPOIEmMinutosIncrementadoComValoresAnteriores =
									tempoDentroDoPOIEmMinutosIncrementadoComValoresAnteriores?.plus(
										tempoDentroDoPOIEmMinutos
									)

								tempoDentroDoPOIEmMinutosIncrementadoComValoresAnteriores?.let { it1 ->
									mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo]?.replace(
										it.nome,
										tempoDentroDoPOIEmMinutosIncrementadoComValoresAnteriores
									)
								}
							} else if (mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo].isNullOrEmpty()) {
								mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo] = mutableMapOf(Pair(it.nome, tempoDentroDoPOIEmMinutos))
							} else {
								var listaJaExistente: MutableMap<String, Long> = mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo]!!.toMutableMap()
								listaJaExistente?.put(it.nome, tempoDentroDoPOIEmMinutos)
								mapTempoPlacaLocal[posicaoVeiculo.placaVeiculo] = listaJaExistente
							}

						}
					}
				}
				i += 1;
			}
		}
		return mapTempoPlacaLocal
	}

	private fun converterEmDto(mapPlacaVeiculoPontoInteresseTempo: MutableMap<String, MutableMap<String, Long>>) : MutableMap<String, List<PontoInteresseTempoDto>> {
		var mutableMap : MutableMap<String, List<PontoInteresseTempoDto>> = mutableMapOf<String, List<PontoInteresseTempoDto>>()

		mapPlacaVeiculoPontoInteresseTempo.forEach{
			var pontoInteresseTempoDtoLista = mutableListOf<PontoInteresseTempoDto>()
			for(mapNomeLocalTempo in it.value){
				pontoInteresseTempoDtoLista.add(PontoInteresseTempoDto(mapNomeLocalTempo.key,mapNomeLocalTempo.value))
			}
			mutableMap.put(it.key,pontoInteresseTempoDtoLista)
		}
		return mutableMap
	}

	private fun separarPorPlacaDeVeiculo(posicaoVeiculoList: MutableList<PosicaoVeiculo>): SetValuedMap<String, PosicaoVeiculo> {

		var map: SetValuedMap<String, PosicaoVeiculo> = HashSetValuedHashMap()

		for (posicao in posicaoVeiculoList) {
			map.put(posicao.placaVeiculo, posicao)
		}

		return map
	}

	private fun calcularTempoEntreDuasDatasEmMinutos(dataPontoAtual: LocalDateTime,dataPontoAnterior: LocalDateTime): Long {
		return ChronoUnit.MINUTES.between(dataPontoAtual, dataPontoAnterior)
	}

	private fun estaoDentroDoCirculo(pontoDeInteresse: PontoDeInteresse,posicaoVeiculoLista: List<PosicaoVeiculo>): Boolean {
		return if (posicaoVeiculoLista.size == 2) {
			estaDentroDoCirculo(
				converterEmCirculo(pontoDeInteresse),
				converterEmPonto(posicaoVeiculoLista[0])
			) &&
					estaDentroDoCirculo(
						converterEmCirculo(pontoDeInteresse),
						converterEmPonto(posicaoVeiculoLista[1])
					)
		} else {
			false
		}
	}

	private fun estaDentroDoCirculo(circulo: Polygon, ponto: Geometry): Boolean {
		return circulo.contains(ponto)
	}

	private fun converterEmPonto(posicaoVeiculo: PosicaoVeiculo): Geometry {
		return converterEmPonto(posicaoVeiculo.latitude, posicaoVeiculo.longitude)
	}

	private fun converterEmPonto(latitude: Double, longitude: Double): Geometry {
		val geometryFactory = GeometryFactory()
		return geometryFactory.createPoint(Coordinate(latitude, longitude))
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

}
