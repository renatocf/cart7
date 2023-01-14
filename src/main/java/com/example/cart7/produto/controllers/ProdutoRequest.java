package com.example.cart7.produto.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProdutoRequest(
        @NotNull @Pattern(regexp = ".{3,50}") String titulo,
        @NotNull @Min(value = 0) @Max(value = 10000) Double preco
) {
}
