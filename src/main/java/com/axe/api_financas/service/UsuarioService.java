package com.axe.api_financas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axe.api_financas.dto.UsuarioRequestDto;
import com.axe.api_financas.dto.UsuarioResponseDto;
import com.axe.api_financas.model.RegraCategorizacao;
import com.axe.api_financas.model.Usuario;
import com.axe.api_financas.repository.RegraCategorizacaoRepository;
import com.axe.api_financas.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RegraCategorizacaoRepository regraRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RegraCategorizacaoRepository regraRepository) {
        this.usuarioRepository = usuarioRepository;
        this.regraRepository = regraRepository;
    }

    @Transactional
    public UsuarioResponseDto cadastrarUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setSenha(usuarioRequestDto.senha());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        criarRegrasPadrao(usuarioSalvo);
        return new UsuarioResponseDto(usuarioSalvo);
    }

    private void criarRegrasPadrao(Usuario usuario){
        List<RegraCategorizacao> regrasPadrao = List.of(
            new RegraCategorizacao(null, ".*(ifood|mcdonalds|burger king|pizza).*", "Alimentação", usuario),
            new RegraCategorizacao(null, ".*(uber|99|petrobras|shell|ipiranga).*", "Transporte", usuario),
            new RegraCategorizacao(null, ".*(netflix|spotify|amazon prime|disney|meli).*", "Assinaturas", usuario),
            new RegraCategorizacao(null, ".*(cemig|copasa|enel|vivo|claro|tim|neoenergia|sabesp).*", "Contas Residenciais", usuario),
            new RegraCategorizacao(null, ".*(farmacia|drogaria|pague menos).*", "Saúde", usuario)
        );

        regraRepository.saveAll(regrasPadrao);
    }
}
