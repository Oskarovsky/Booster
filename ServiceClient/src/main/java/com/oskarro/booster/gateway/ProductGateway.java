package com.oskarro.booster.gateway;

import com.oskarro.apiclient.model.Product;
import com.oskarro.commons.common.BaseGateway;
import com.oskarro.commons.common.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductGateway extends BaseGateway<Product, Integer> {

    public ProductGateway(BaseService<Product, Integer> service) {
        super(service);
    }

}
