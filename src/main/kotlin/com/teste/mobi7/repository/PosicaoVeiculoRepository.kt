package com.teste.mobi7.repository

import com.teste.mobi7.model.PosicaoVeiculo
import org.springframework.data.jpa.repository.JpaRepository

interface PosicaoVeiculoRepository : JpaRepository<PosicaoVeiculo, Int>
