package com.oskarro.booster.repository;

import com.oskarro.booster.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
