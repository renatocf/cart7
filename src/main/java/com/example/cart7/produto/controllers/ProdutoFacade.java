package com.example.cart7.produto.controllers;

import com.example.cart7.produto.models.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoFacade {
    ProdutoConverter produtoConverter;
    ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoFacade(ProdutoConverter produtoConverter, ProdutoRepository produtoRepository) {
        this.produtoConverter = produtoConverter;
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponse> listProdutos() {
        var produtos = produtoRepository.getAll();
        return produtoConverter.toProdutoResponses(produtos);
    }

    public ProdutoResponse showProduto(String webCode) {
        var id = produtoConverter.webCode2id(webCode);
        var produto = produtoRepository.findById(id);
        return produtoConverter.toProdutoResponse(produto);
    }

    public ProdutoResponse addProduto(ProdutoRequest request) {
        var creation = produtoConverter.toProdutoCreation(request);
        var newProduto = produtoRepository.create(creation);
        return produtoConverter.toProdutoResponse(newProduto);
    }

    public ProdutoResponse replaceProduto(ProdutoRequest request, String webCode) {
        var id = produtoConverter.webCode2id(webCode);
        var update = produtoConverter.toProdutoUpdate(request);
        var updatedProduto = produtoRepository.update(id, update);
        return produtoConverter.toProdutoResponse(updatedProduto);
    }

    public ProdutoResponse changeProduto(PartialProdutoRequest request, String webCode) {
        var id = produtoConverter.webCode2id(webCode);
        var update = produtoConverter.toProdutoUpdate(request);
        var updatedProduto = produtoRepository.update(id, update);
        return produtoConverter.toProdutoResponse(updatedProduto);
    }

    public ProdutoResponse deleteProduto(String webCode) {
        var id = produtoConverter.webCode2id(webCode);
        var deletedProduto = produtoRepository.delete(id);
        return produtoConverter.toProdutoResponse(deletedProduto);
    }
}