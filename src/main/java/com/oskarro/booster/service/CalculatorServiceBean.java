package com.oskarro.booster.service;

import com.oskarro.booster.dto.CounterDto;
import com.oskarro.booster.model.Customer;
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
            dto.setEnergy((int) (dto.getEnergy() + t.getProduct().getEnergy() * t.getPortion()));
            dto.setFat(dto.getFat() + t.getProduct().getFat() * t.getPortion());
            dto.setProtein(dto.getProtein() + t.getProduct().getProtein() * t.getPortion());
            dto.setCarbs(dto.getCarbs() + t.getProduct().getCarbs() * t.getPortion());
            dto.getMeals().add(t);
        });
        return dto;
    }

    @Override
    public CounterDto calculateDietForCustomer(final Customer customer) {
        CounterDto dto = new CounterDto();
        // TODO calculate diet
        return dto;
    }

    @Override
    public CounterDto calculateTheNutrientsFromToday(final Customer customer) {
        CounterDto dto = new CounterDto();
        mealService.getMealsByCustomerFromToday(customer).forEach(t -> {
            dto.setEnergy((int) (dto.getEnergy() + t.getProduct().getEnergy() * t.getPortion()));
            dto.setFat(dto.getFat() + t.getProduct().getFat() * t.getPortion());
            dto.setProtein(dto.getProtein() + t.getProduct().getProtein() * t.getPortion());
            dto.setCarbs(dto.getCarbs() + t.getProduct().getCarbs() * t.getPortion());
            dto.getMeals().add(t);
        });
        return dto;
    }

}
