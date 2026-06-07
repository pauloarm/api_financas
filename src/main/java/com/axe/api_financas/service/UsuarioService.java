package com.axe.api_financas.service;

import org.springframework.stereotype.Service;

import com.axe.api_financas.dto.UsuarioRequestDto;
import com.axe.api_financas.dto.UsuarioResponseDto;
import com.axe.api_financas.model.Usuario;
import com.axe.api_financas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDto cadastrarUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setSenha(usuarioRequestDto.senha());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDto(
            usuarioSalvo.getId(),
            usuarioSalvo.getNome(),
            usuarioSalvo.getEmail()
        );
    }
}
