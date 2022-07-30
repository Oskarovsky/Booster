package com.oskarro.booster.service;

import com.oskarro.booster.model.Product;

public interface ProductService {

    Product getById(Integer productId);

    Product save(Product product);
}
