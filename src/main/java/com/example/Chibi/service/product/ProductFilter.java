package com.example.Chibi.service.product;

import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.util.FilterService;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class ProductFilter {
    public static Predicate<ProductModel> pesquisa(String nome) {
        return (product) -> {
            boolean nameFilter = product.getNome().toLowerCase().trim().contains(nome.toLowerCase().trim());
            boolean descriptionFilter = product.getDescricao().toLowerCase().trim().contains(nome.toLowerCase().trim());

            return nameFilter || descriptionFilter;
        };
    }

    public static Predicate<ProductModel> precoMin(double preco) {
        return (product) -> product.getPreco() >= preco;
    }

    public static Predicate<ProductModel> precoMax(double preco) {
        return (product) -> product.getPreco() <= preco;
    }

    public static Predicate<ProductModel> desconto() {
        return (product) -> product.getDesconto() > 0;
    }

    public static Predicate<ProductModel> categoria(List<String> categorias) {
        return (product) -> new HashSet<>(product.getCategoria()).containsAll(categorias);
    }

    public static Predicate<ProductModel> marca(String marca) {
        return (product) -> product.getMarca().toLowerCase().trim().contains(marca.toLowerCase().trim());
    }

    public static List<ProductModel> applyFilters(List<ProductModel> models, List<Predicate<ProductModel>> filter_list) {
        FilterService<ProductModel> filterService = new FilterService<>();
        return filterService.filter(models, filter_list);
    }
}
