package com.axe.api_financas.dto;

import com.axe.api_financas.model.Usuario;

public record UsuarioResponseDto(
    Long id,
    String nome,
    String email
) {

    public UsuarioResponseDto(Usuario usuario) {
       this(usuario.getId(), usuario.getNome(),usuario.getEmail());
    }
    
}
