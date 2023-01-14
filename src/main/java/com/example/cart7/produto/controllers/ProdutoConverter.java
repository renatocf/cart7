package com.example.cart7.produto.controllers;

import com.example.cart7.produto.models.Produto;
import com.example.cart7.produto.models.ProdutoCreation;
import com.example.cart7.produto.models.ProdutoUpdate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoConverter {
    private final long SALT = 4242;

    ProdutoCreation toProdutoCreation(ProdutoRequest request) {
        return new ProdutoCreation(request.titulo(), request.preco());
    }

    ProdutoUpdate toProdutoUpdate(ProdutoRequest request) {
        return new ProdutoUpdate(request.titulo(), request.preco());
    }

    ProdutoUpdate toProdutoUpdate(PartialProdutoRequest request) {
        return new ProdutoUpdate(request.titulo(), request.preco());
    }

    List<ProdutoResponse> toProdutoResponses(List<Produto> produtos) {
        return produtos.stream().map(this::toProdutoResponse).collect(Collectors.toList());
    }

    ProdutoResponse toProdutoResponse(Produto produto) {
        var webCode = id2WebCode(produto.id());
        return new ProdutoResponse(webCode, produto.titulo(), produto.preco());
    }

    String id2WebCode(long id) {
        var saltedId = id + SALT;
        return Long.toHexString(saltedId);
    }

    long webCode2id(String webCode) {
        var saltedId = Long.valueOf(webCode, 16);
        return saltedId - SALT;
    }
}
