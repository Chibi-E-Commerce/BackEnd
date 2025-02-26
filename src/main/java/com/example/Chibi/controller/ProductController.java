package com.example.Chibi.controller;

import com.example.Chibi.dto.ProductDto;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/produto")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public List<ProductModel> getAll() {
        return productService.findAll();
    }

    @GetMapping
    public ProductModel getById(@RequestParam String id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductModel create(@RequestBody ProductDto productDto) {
        return productService.insert(productDto);
    }

    @PutMapping
    public ProductModel update(@RequestParam String id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }

    @DeleteMapping
    public void delete(@RequestParam String id) {
        productService.delete(id);
    }
}
