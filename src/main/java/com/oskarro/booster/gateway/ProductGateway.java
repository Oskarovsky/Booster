package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductGateway {

    ProductService productService;

    public ProductGateway(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/product")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}
