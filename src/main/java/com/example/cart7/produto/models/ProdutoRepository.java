package com.example.cart7.produto.models;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Repository
public class ProdutoRepository {
    private final ProdutoFactory produtoFactory;

    private final List<Produto> produtos = new ArrayList<>();

    @Autowired
    ProdutoRepository(ProdutoFactory produtoFactory) {
        this.produtoFactory = produtoFactory;
    }

    public List<Produto> getAll() {
        return produtos;
    }

    public Produto findById(long id) {
        var idx = findPosition(id);

        if (idx.isEmpty()) {
            throw new ValidationException("Produto não encontrado!");
        }

        return produtos.get(idx.getAsInt());
    }

    public Produto create(ProdutoCreation creation) {
        Produto newProduto = produtoFactory.createFrom(creation);
        produtos.add(newProduto);
        return newProduto;
    }

    public Produto update(long id, ProdutoUpdate update) {
        var idx = findPosition(id);

        if (idx.isEmpty()) {
            throw new ValidationException("Produto não encontrado!");
        }

        Produto produto = produtos.get(idx.getAsInt());
        Produto updatedProduto = produtoFactory.createFrom(produto, update);
        produtos.set(idx.getAsInt(), updatedProduto);

        return updatedProduto;
    }

    public Produto delete(long id) {
        var idx = findPosition(id);

        if (idx.isEmpty()) {
            throw new ValidationException("Produto não encontrado!");
        }

        return produtos.remove(idx.getAsInt());
    }

    OptionalInt findPosition(long id) {
        return IntStream.range(0, produtos.size())
                 .filter(idx -> id == produtos.get(idx).id())
                 .findFirst();
    }
}
