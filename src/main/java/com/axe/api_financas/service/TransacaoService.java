package com.axe.api_financas.service;

import org.springframework.stereotype.Service;

import com.axe.api_financas.dto.TransacaoRequestDto;
import com.axe.api_financas.dto.TransacaoResponseDto;
import com.axe.api_financas.model.Transacao;
import com.axe.api_financas.model.Usuario;
import com.axe.api_financas.repository.TransacaoRepository;
import com.axe.api_financas.repository.UsuarioRepository;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RegraCategorizacaoService regraCategorizacaoService;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository, RegraCategorizacaoService regraCategorizacaoService){
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.regraCategorizacaoService = regraCategorizacaoService;
    }

    public TransacaoResponseDto cadastrarTransacaoManual(TransacaoRequestDto transacaoRequestDto){
        Usuario usuario = usuarioRepository.findById(transacaoRequestDto.usuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Transacao transacao = new Transacao();
        transacao.setDescricao(transacaoRequestDto.descricao());
        transacao.setValor(transacaoRequestDto.valor());
        transacao.setData(transacaoRequestDto.data());
        transacao.setTipo(transacaoRequestDto.tipo());
        transacao.setUsuario(usuario);

        String categoriaDefinida = regraCategorizacaoService.categorizarTransacao(transacaoRequestDto.descricao(), usuario.getId());
        transacao.setCategoria(categoriaDefinida);

        return new TransacaoResponseDto(transacaoRepository.save(transacao));

    }
}
