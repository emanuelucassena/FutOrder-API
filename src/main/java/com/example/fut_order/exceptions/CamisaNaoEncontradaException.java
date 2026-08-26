package com.example.fut_order.exceptions;

public class CamisaNaoEncontradaException extends RuntimeException {
    public CamisaNaoEncontradaException(Long id) {
        super("Camisa não encontrada com ID: " + id);

    }
}
