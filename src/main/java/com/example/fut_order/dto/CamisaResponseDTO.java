package com.example.fut_order.dto;

import com.example.fut_order.model.Camisa;
import com.example.fut_order.model.enums.TipoVersao;

import java.math.BigDecimal;

public record CamisaResponseDTO(

        Long id,
        String time,
        String temporada,
        TipoVersao versao,
        BigDecimal precoBase,
        Integer qtdEstoque

) {

    // Construtor auxiliar: Transforma diretamente a Entidade JPA em DTO de Resposta.
    // O 'this(...)' chama o construtor padrão do record repassando os valores da entidade.
    public CamisaResponseDTO(Camisa camisa) {
        this(
                camisa.getId(),
                camisa.getTime(),
                camisa.getTemporada(),
                camisa.getVersao(),
                camisa.getPrecoBase(),
                camisa.getQtdEstoque()
        );
    }


}