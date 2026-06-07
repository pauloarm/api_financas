package com.axe.api_financas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.axe.api_financas.model.TipoTransacao;

public record TransacaoRequestDto(
    String descricao,
    BigDecimal valor,
    LocalDate data,
    TipoTransacao tipo
) {
    
}
