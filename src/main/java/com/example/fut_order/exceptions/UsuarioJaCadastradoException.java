package com.example.fut_order.exceptions;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException(String email) {
        super("Usuário já cadastrado com Email: " + email);
    }
}
