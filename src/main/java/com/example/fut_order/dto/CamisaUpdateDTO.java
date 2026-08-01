package com.example.fut_order.dto;

import com.example.fut_order.model.enums.TipoVersao;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CamisaUpdateDTO(

        String time,
        String temporada,
        TipoVersao versao,

        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal precoBase,

        @PositiveOrZero(message = "A quantidade não pode ser zero")
        Integer qtdEstoque

) {
}
