package com.oskarro.booster.service;

import com.oskarro.booster.dto.CounterDto;
import com.oskarro.booster.model.Meal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorServiceBean implements CalculatorService {

    private final MealService mealService;

    public CalculatorServiceBean(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public CounterDto calculateTheNutrients(final List<Meal> meals) {
        CounterDto dto = new CounterDto();
        meals.forEach(t -> {
            dto.setEnergy(dto.getEnergy() + t.getProduct().getEnergy());
            dto.setFat(dto.getFat() + t.getProduct().getFat());
            dto.setProtein(dto.getProtein() + t.getProduct().getProtein());
            dto.setCarbs(dto.getCarbs() + t.getProduct().getCarbs());
            dto.getMeals().add(t);
        });
        return dto;
    }
}
