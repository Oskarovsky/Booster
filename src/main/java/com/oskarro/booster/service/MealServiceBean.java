package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.MealRepository;
import org.springframework.stereotype.Service;

@Service
public class MealServiceBean implements MealService {

    public MealRepository mealRepository;

    public MealServiceBean(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void saveMeal(Meal meal) {
        mealRepository.addMeal(meal);
    }

}
