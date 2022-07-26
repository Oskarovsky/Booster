package com.oskarro.booster.repository;

import com.oskarro.booster.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
