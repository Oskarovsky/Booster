package com.oskarro.booster.gateway;

import com.oskarro.booster.service.MealService;
import com.oskarro.booster.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/calc")
public class CalculatorGateway {

    private final MealService mealService;
    private final ProductService productService;

    public CalculatorGateway(final MealService mealService, final ProductService productService) {
        this.mealService = mealService;
        this.productService = productService;
    }


}
