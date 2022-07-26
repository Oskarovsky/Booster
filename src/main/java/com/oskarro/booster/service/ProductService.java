package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    void saveProduct(Product product);
}
