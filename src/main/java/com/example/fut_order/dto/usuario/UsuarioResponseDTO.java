package com.example.fut_order.dto.usuario;

import com.example.fut_order.model.Usuario;
import com.example.fut_order.model.enums.TipoPapel;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        TipoPapel papel

) {

    public UsuarioResponseDTO(Usuario usuario){

        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPapel()
        );

    }

}
