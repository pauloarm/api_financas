package com.axe.api_financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axe.api_financas.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<TransacaoRepository> findByUsuarioId(Long usuarioId);
}
