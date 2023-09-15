package com.teste.mobi7.service

import com.teste.mobi7.dto.PosicaoVeiculoDto
import com.teste.mobi7.model.extensions.converterDto
import com.teste.mobi7.model.extensions.converterParaModel
import com.teste.mobi7.repository.PosicaoVeiculoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PosicaoVeiculoService(
    val posicaoVeiculoRepository: PosicaoVeiculoRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun criar(posicaoVeiculoDto: PosicaoVeiculoDto): PosicaoVeiculoDto {
        log.info("m=criar; step=start; posicaoVeiculoDto=${posicaoVeiculoDto}")
        val posicaoVeiculo = posicaoVeiculoDto.converterParaModel();
        val posicaoVeiculoCriado = posicaoVeiculoRepository.save(posicaoVeiculo)
        log.info("m=criar; step=finished; posicaoVeiculoCriado=${posicaoVeiculoCriado}")
        return posicaoVeiculoCriado.converterDto()
    }
}