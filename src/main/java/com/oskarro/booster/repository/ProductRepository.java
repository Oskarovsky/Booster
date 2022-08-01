package com.oskarro.booster.repository;

import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {

}
