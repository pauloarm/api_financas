package com.axe.api_financas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.axe.api_financas.model.TipoTransacao;
import com.axe.api_financas.model.Transacao;

public record TransacaoResponseDto(
    Long id,
    String descricao,
    BigDecimal valor,
    LocalDate data,
    String categoria,
    TipoTransacao tipo
) {
    public TransacaoResponseDto(Transacao transacao){
        this(transacao.getId(), transacao.getDescricao(), transacao.getValor(), transacao.getData(), transacao.getCategoria(), transacao.getTipo());
    }
}
