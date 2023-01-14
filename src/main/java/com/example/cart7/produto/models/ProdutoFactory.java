package com.example.cart7.produto.models;

import org.springframework.stereotype.Component;

@Component
public class ProdutoFactory {
    long idCounter = 0;

    Produto createFrom(ProdutoCreation creation) {
        return new Produto(
                generateId(),
                creation.titulo(),
                creation.preco()
        );
    }

    Produto createFrom(Produto produto, ProdutoUpdate update) {
        return new Produto(
                produto.id(),
                update.titulo().orElse(produto.titulo()),
                update.preco().orElse(produto.preco())
        );
    }

    long generateId() {
        return idCounter++;
    }
}
