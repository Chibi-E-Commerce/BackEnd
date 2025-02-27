package com.example.Chibi.controller;

import com.example.Chibi.dto.ProductDto;
import com.example.Chibi.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/produto")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> getAll() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
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
