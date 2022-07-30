package com.oskarro.booster.service;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.BaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
public class ProductServiceBean extends BaseServiceBean<Product, Integer> implements ProductService {

    public ProductServiceBean(BaseRepository<Product, Integer> baseRepository) {
        super(baseRepository);
    }
}
