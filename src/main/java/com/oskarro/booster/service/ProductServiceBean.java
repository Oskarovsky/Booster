package com.oskarro.booster.service;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceBean implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
