package com.example.Chibi.controller;

import com.example.Chibi.dto.ProductDto;
import com.example.Chibi.dto.ProductSearchDto;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequestMapping("/produto")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> getAll() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @PostMapping("/search")
    public List<ProductDto> search(@RequestBody ProductSearchDto productSearchDto) {
        List<Predicate<ProductModel>> predicates = productSearchDto.breakdown();
        List<ProductModel> products = productService.search(predicates);
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping
    public ProductDto getById(@RequestParam String id) {
        return new ProductDto(productService.findById(id));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return new ProductDto(productService.insert(productDto));
    }

    @PutMapping
    public ProductDto update(@RequestParam String id, @RequestBody ProductDto productDto) {
        return new ProductDto(productService.update(id, productDto));
    }

    @DeleteMapping
    public void delete(@RequestParam String id) {
        productService.delete(id);
    }
}
