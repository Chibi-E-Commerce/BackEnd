package com.example.Chibi.service.product;

import com.example.Chibi.dto.ProductDto;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel insert(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        return productRepository.save(productModel);
    }

    public ProductModel findByNome(String nome) {
        return productRepository.findByNome(nome).orElse(null);
    }

    public ProductModel findById(String id) {
        return productRepository.findById(new ObjectId(id)).orElse(null);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public List<ProductModel> sortAll() {
        List<ProductModel> productModels = findAll();
        Collections.sort(productModels);
        return productModels;
    }

    public List<ProductModel> search(List<Predicate<ProductModel>> filters) {
        List<ProductModel> products = findAll();
        return ProductFilter.applyFilters(products, filters);
    }

    public List<ProductModel> searchRestricted(List<Predicate<ProductModel>> filters) {
        List<ProductModel> products = findAll();
        List<ProductModel> productsFiltered = new ArrayList<>();
        for (Predicate<ProductModel> filter : filters) {
            productsFiltered = products.stream().filter(filter).toList();
            if (!productsFiltered.isEmpty()) {
                return productsFiltered;
            }
        }
        return productsFiltered;

    }

    public ProductModel update(String id, ProductDto productDto) {
        ProductModel productModel = findById(id);
        if (productModel != null) {
            BeanUtils.copyProperties(productDto, productModel);
            return productRepository.save(productModel);
        }
        return null;
    }

    public boolean delete(String nome) {
        ProductModel productModel = findByNome(nome);
        if (productModel != null) {
            productRepository.delete(productModel);
            return true;
        }
        return false;
    }

}
