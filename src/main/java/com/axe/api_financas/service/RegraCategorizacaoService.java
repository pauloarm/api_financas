package com.axe.api_financas.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.axe.api_financas.model.RegraCategorizacao;
import com.axe.api_financas.repository.RegraCategorizacaoRepository;

@Service
public class RegraCategorizacaoService {

    private final RegraCategorizacaoRepository regraRepository;

    public RegraCategorizacaoService(RegraCategorizacaoRepository regraRepository) {
        this.regraRepository = regraRepository;
    }

    public String categorizarTransacao(String descricao, Long usuarioId){
        // Verifica se a descrição é nula ou vazia
        if(descricao == null || descricao.isBlank()){
            return "Sem categoria";
        }
        // Busca as regras de categorização do usuário
        List<RegraCategorizacao> regras = regraRepository.findByUsuarioId(usuarioId);
        
        // Itera sobre as regras e tenta encontrar uma correspondência
        for (RegraCategorizacao regra : regras){
            // Tenta compilar o padrão regex e verificar se a descrição corresponde
            try {
                // Compila o padrão regex da regra, ignorando maiúsculas e minúsculas
                Pattern pattern = Pattern.compile(regra.getPadrao_regex(), Pattern.CASE_INSENSITIVE);
                // Cria um matcher para a descrição da transação 
                Matcher matcher = pattern.matcher(descricao);

                if(matcher.find()){
                    return regra.getCategoria();
                }
                
            } catch (Exception e) {
                System.err.println("Erro ao processar regex: " + regra.getPadrao_regex());
            }
        }
        return "Sem Categoria";
    }

    public RegraCategorizacao salvar (RegraCategorizacao regraCategorizacao){
        return regraRepository.save(regraCategorizacao);
    }
    
}
