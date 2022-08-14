package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseService;
import com.oskarro.booster.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product, Integer> {

    List<Product> getAllProductsByProviderId(Integer providerId);

    List<Product> getAllProductsByProviderName(String providerName);
}
