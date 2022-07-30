package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;

import java.util.List;

public interface MealService {

    Meal getById(Integer mealId);

    Meal save(Meal meal);
}
