package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseServiceBean;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.common.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceBean extends BaseServiceBean<Product, Integer> implements ProductService {

    public ProductServiceBean(BaseRepository<Product, Integer> baseRepository) {
        super(baseRepository);
    }
}
