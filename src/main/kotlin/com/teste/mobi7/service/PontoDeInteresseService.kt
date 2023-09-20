package com.teste.mobi7.service

import com.teste.mobi7.dto.PontoDeInteresseDto
import com.teste.mobi7.model.PontoDeInteresse
import com.teste.mobi7.model.extensions.converterParaDto
import com.teste.mobi7.model.extensions.converterParaModel
import com.teste.mobi7.repository.PontoDeInteresseRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PontoDeInteresseService(
    val pontoDeInteresseRepository: PontoDeInteresseRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun criar(pontoDeInteresseDto: PontoDeInteresseDto): PontoDeInteresseDto {
        log.info("m=criar; step=start; pontoDeInteresseDto=${pontoDeInteresseDto}")
        val pontoDeInteresse = pontoDeInteresseDto.converterParaModel()
        val pontoDeInteresseCriado = pontoDeInteresseRepository.save(pontoDeInteresse)
        log.info("m=criar; step=finished; pontoDeInteresseCriado=${pontoDeInteresseCriado}")
        return pontoDeInteresseCriado.converterParaDto()
    }

    fun buscarTodos() : MutableList<PontoDeInteresse>{
        // todo - colocar log
        return pontoDeInteresseRepository.findAll()
    }
}