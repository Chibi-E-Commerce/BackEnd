package com.example.Chibi.dto;

import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.product.ProductFilter;
import com.example.Chibi.service.util.FilterBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchDto implements Search<ProductModel> {
    public String pesquisa;
    public Double precoMin;
    public Double precoMax;
    public Integer inDesconto;
    public String categoria;
    public String marca;

    @Override
    public List<Predicate<ProductModel>> breakdown() {
        FilterBuilder<ProductModel> filterBuilder = new FilterBuilder<>();
        filterBuilder.add(ProductFilter.pesquisa(pesquisa), !pesquisa.isBlank());
        filterBuilder.add(ProductFilter.precoMax(precoMax), precoMax != 0);
        filterBuilder.add(ProductFilter.precoMin(precoMin), precoMin != 0);
        filterBuilder.add(ProductFilter.desconto(), inDesconto == 1);
        filterBuilder.add(ProductFilter.categoria(categoria), !categoria.isBlank());
        filterBuilder.add(ProductFilter.marca(marca), !marca.isBlank());

        return filterBuilder.unwrap();
    }
}
