package com.oskarro.booster.service;

import com.oskarro.booster.dto.CounterDto;

public class CalculatorServiceBean implements CalculatorService {

    private final MealService mealService;

    public CalculatorServiceBean(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public CounterDto calculateTheNutrients() {
        return null;
    }
}
