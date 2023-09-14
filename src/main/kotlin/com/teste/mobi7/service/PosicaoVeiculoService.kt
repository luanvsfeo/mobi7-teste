package com.teste.mobi7.service

import com.teste.mobi7.dto.PosicaoVeiculoDto
import com.teste.mobi7.repository.PosicaoVeiculoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PosicaoVeiculoService(
    val posicaoVeiculoRepository: PosicaoVeiculoRepository
) {
    // colocar log

    private val log = LoggerFactory.getLogger(this::class.java)


    fun criar(posicaoVeiculoDto: PosicaoVeiculoDto): PosicaoVeiculoDto {
        return posicaoVeiculoRepository.save(posicaoVeiculoDto.converterParaModel()).converterDto()
    }
}