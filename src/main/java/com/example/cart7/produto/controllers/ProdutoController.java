package com.example.cart7.produto.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
public class ProdutoController {
    ProdutoFacade produtoFacade;

    @Autowired
    public ProdutoController(ProdutoFacade produtoFacade) {
        this.produtoFacade = produtoFacade;
    }

    @GetMapping("/produtos")
    public List<ProdutoResponse> listProdutos() {
        return produtoFacade.listProdutos();
    }

    @GetMapping("/produtos/{webCode}")
    public ProdutoResponse showProduto(@PathVariable String webCode) {
        try {
            return produtoFacade.showProduto(webCode);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponse>
    addProduto(@RequestBody ProdutoRequest request) {
        var response = produtoFacade.addProduto(request);
        var locationUri = URI.create("/produtos/" + response.webCode());
        return ResponseEntity.created(locationUri).body(response);
    }

    @PutMapping ("/produtos/{webCode}")
    public ProdutoResponse replaceProduto(@Valid @RequestBody ProdutoRequest request, @PathVariable String webCode) {
        try {
            return produtoFacade.replaceProduto(request, webCode);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping ("/produtos/{webCode}")
    public ProdutoResponse changeProduto(@Valid @RequestBody PartialProdutoRequest request, @PathVariable String webCode) {
        try {
            return produtoFacade.changeProduto(request, webCode);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/produtos/{webCode}")
    public ProdutoResponse removeProduto(@PathVariable String webCode) {
        try {
            return produtoFacade.deleteProduto(webCode);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
