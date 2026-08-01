package com.example.fut_order.dto;

import com.example.fut_order.model.enums.TipoVersao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CamisaCreateDTO(

        // @NotBlank: Garante que o texto não seja nulo e nem composto apenas por espaços em branco (" ").
        // @Size: Limita a quantidade mínima e máxima de caracteres aceitos.
        @NotBlank(message = "O nome do time é obrigatório!")
        @Size(min = 3, max = 100, message = "O time deve ter entre 3 e 100 caracteres")
        String time,

        @NotBlank(message = "A temporada é obrigatória!")
        @Size(max = 20, message = "A temporada deve ter no máximo 20 caracteres")
        String temporada,

        // @NotNull: Garante apenas que o valor não seja nulo (usado em objetos, Enums e números).
        // Não usei @NotBlank nem @Size aqui, pois 'TipoVersao' é um Enum e não uma String.
        @NotNull(message = "A versão é obrigatória!")
        TipoVersao versao,

        // @Positive: Valida se o número é estritamente maior que zero (> 0).
        // @Digits: Valida a precisão decimal de números.
        // 'integer = 6' permite até 6 inteiros (ex: 999999) e 'fraction = 2' limita a 2 casas decimais.
        @NotNull(message = "O preço base é obrigatório!")
        @Positive(message = "O preço deve ser maior que zero")
        @Digits(integer = 6, fraction = 2, message = "O preço deve ter no máximo 6 dígitos inteiros e 2 casas decimais")
        BigDecimal precoBase,

        // @PositiveOrZero: Aceita valores maiores ou iguais a zero (>= 0), permitindo cadastrar com estoque 0.
        @NotNull(message = "A quantidade de estoque é obrigatória")
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        Integer qtdEstoque

) {
}