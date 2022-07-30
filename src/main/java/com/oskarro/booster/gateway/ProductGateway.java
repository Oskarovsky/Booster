package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Product;
import com.oskarro.booster.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductGateway {

    ProductService productService;

    public ProductGateway(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
