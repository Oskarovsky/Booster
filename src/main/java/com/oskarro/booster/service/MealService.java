package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;

public interface MealService {

    Meal getMealById(Integer mealId);
    Meal saveMeal(Meal meal);
}
