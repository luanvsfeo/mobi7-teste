package com.teste.mobi7.service

import com.teste.mobi7.model.PontoDeInteresse
import com.teste.mobi7.model.PosicaoVeiculo
import com.teste.mobi7.repository.PontoDeInteresseRepository
import com.teste.mobi7.repository.PosicaoVeiculoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.math.pow

@Service
class RelatorioService(
    val posicaoVeiculoRepository: PosicaoVeiculoRepository,
    val pontoDeInteresseRepository: PontoDeInteresseRepository,
) {

    // todo - depois trocar para as services

    fun estaDentroDoRaio(pontoDeInteresse: PontoDeInteresse, posicaoVeiculo: PosicaoVeiculo) : Boolean {




        // o circulo
        pontoDeInteresse.longitude // x
        pontoDeInteresse.latitude // y
        pontoDeInteresse.raioEmMetros

        //ponto
        posicaoVeiculo.longitude //x
        posicaoVeiculo.latitude // y

        val dx: Double = posicaoVeiculo.longitude - pontoDeInteresse.longitude
        val dy: Double = posicaoVeiculo.latitude - pontoDeInteresse.latitude

        return ((dx.pow(2.toDouble()) + dy.pow(2.toDouble())) < pontoDeInteresse.raioEmMetros.toDouble().pow(2.toDouble()))
    }

    fun buscarPorFiltro() {


        val resposta = estaDentroDoRaio(
            PontoDeInteresse(1,"aasdad",100,-25.36496636999715,-51.46980205405271),
            PosicaoVeiculo(1,"teste", LocalDateTime.now(),0, -25.368298,-51.470125,false)
        )

      //  val listaPosicoes = posicaoVeiculoRepository.findAll()
        //val listaPoi = pontoDeInteresseRepository.findAll()


        // buscar as posicoes por data e/ou placa de veiculo

        /*
            busca as posicoes
            separa por veiculo ou separa por poi
            faz o calculo do poi
            retorna a lista de poi por veiculo
         */
    }
}