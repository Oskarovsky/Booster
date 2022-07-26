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

    public List<Product> getAll() {
        return null;
    }


    public void saveProduct(Product product) {
        productRepository.addProduct(product);
    }
}
