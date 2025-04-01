package com.example.Chibi.controller;

import com.example.Chibi.dto.product.ProductRequest;
import com.example.Chibi.dto.product.ProductResponse;
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
    public List<ProductResponse> getAll() {
        return productService.findAll().stream().map(ProductResponse::new).toList();
    }

    @GetMapping
    public ProductResponse getById(@RequestParam String id) {
        return new ProductResponse(productService.findById(id));
    }

    @GetMapping("/sort")
    public List<ProductResponse> sortAll() {
        return productService.sortAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/search")
    public List<ProductResponse> search(@RequestBody ProductSearchDto productSearchDto) {
        List<Predicate<ProductModel>> predicates = productSearchDto.breakdown();
        List<ProductModel> products = productService.search(predicates);
        return products.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/search/restricted")
    public List<ProductResponse> searchRestricted(@RequestBody ProductSearchDto productSearchDto) {
        List<Predicate<ProductModel>> predicates = productSearchDto.breakdown();
        List<ProductModel> products = productService.searchRestricted(predicates);
        return products.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest productDto) {
        return new ProductResponse(productService.insert(productDto));
    }

    @PutMapping
    public ProductResponse update(@RequestParam String id,@RequestBody ProductRequest productDto) {
        return new ProductResponse(productService.update(id,productDto));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam String id) {
        return productService.delete(id) ? ResponseEntity.status(200).body(true) : ResponseEntity.status(404).body(false);
    }
}
