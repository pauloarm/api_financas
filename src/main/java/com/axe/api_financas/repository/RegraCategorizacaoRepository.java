package com.axe.api_financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axe.api_financas.model.RegraCategorizacao;

@Repository
public interface RegraCategorizacaoRepository extends JpaRepository<RegraCategorizacao, Long> {
    List<RegraCategorizacao> findByUsuarioId(Long usuarioId);
}
