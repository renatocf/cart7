package com.example.cart7.produto.models;

import java.util.Optional;

public record ProdutoUpdate(Optional<String> titulo, Optional<Double> preco) {
    public ProdutoUpdate(String titulo, Double preco) {
        this(Optional.ofNullable(titulo), Optional.ofNullable(preco));
    }
}