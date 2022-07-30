package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.BaseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductGateway extends BaseGateway<Product> {

    public ProductGateway(BaseRepository<Product> repository) {
        super(repository);
    }
}
