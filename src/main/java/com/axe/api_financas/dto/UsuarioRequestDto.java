package com.axe.api_financas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(
    @NotBlank String nome,
    @NotBlank @Email String email,
    @NotBlank String senha
) {
    
}
