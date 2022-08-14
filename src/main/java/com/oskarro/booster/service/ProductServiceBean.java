package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseServiceBean;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceBean extends BaseServiceBean<Product, Integer> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceBean(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProductsByProviderId(final Integer providerId) {
        return productRepository.findAllByProviderId(providerId);
    }

    @Override
    public List<Product> getAllProductsByProviderName(final String providerName) {
        return productRepository.findAllByProviderName(providerName);
    }
}
