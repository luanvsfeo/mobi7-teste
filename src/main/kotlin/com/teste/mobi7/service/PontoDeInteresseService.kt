package com.teste.mobi7.service

import com.teste.mobi7.dto.PontoDeInteresseDto
import com.teste.mobi7.repository.PontoDeInteresseRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PontoDeInteresseService(
    val pontoDeInteresseRepository: PontoDeInteresseRepository
) {
    // colocar log

    private val log = LoggerFactory.getLogger(this::class.java)


    fun criar(pontoDeInteresseDto: PontoDeInteresseDto): PontoDeInteresseDto {
        return pontoDeInteresseRepository.save(pontoDeInteresseDto.converterParaModel()).converterParaDto()
    }
}