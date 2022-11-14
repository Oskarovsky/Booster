package com.oskarro.booster.service;

import com.oskarro.apiclient.model.Product;
import com.oskarro.commons.common.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product, Integer> {

    List<Product> getAllProductsByProviderId(Integer providerId);

    List<Product> getAllProductsByProviderName(String providerName);
}
