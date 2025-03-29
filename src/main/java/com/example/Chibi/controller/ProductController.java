package com.example.Chibi.controller;

import com.example.Chibi.dto.ProductDto;
import com.example.Chibi.dto.search.ProductSearchDto;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sort")
    public List<ProductDto> sortAll() {
        return productService.sortAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @PostMapping("/search")
    public List<ProductDto> search(@RequestBody ProductSearchDto productSearchDto) {
        List<Predicate<ProductModel>> predicates = productSearchDto.breakdown();
        List<ProductModel> products = productService.search(predicates);
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @PostMapping("/search/restricted")
    public List<ProductDto> searchRestricted(@RequestBody ProductSearchDto productSearchDto) {
        List<Predicate<ProductModel>> predicates = productSearchDto.breakdown();
        List<ProductModel> products = productService.searchRestricted(predicates);
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
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
    public ResponseEntity<Boolean> delete(@RequestParam String nome) {
        return productService.delete(nome) ? ResponseEntity.status(200).body(true) : ResponseEntity.status(404).body(false);
    }
}
