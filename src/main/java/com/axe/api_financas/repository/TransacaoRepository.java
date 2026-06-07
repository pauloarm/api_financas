package com.axe.api_financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoRepository, Long> {
    List<TransacaoRepository> findByUsuarioId(Long usuarioId);
}
