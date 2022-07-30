package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceBean implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NullPointerException("Could not find product with id");
        }
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
