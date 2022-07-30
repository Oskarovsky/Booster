package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import com.oskarro.booster.repository.MealRepository;
import com.oskarro.booster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealServiceBean implements MealService {

    public MealRepository mealRepository;
    public ProductRepository productRepository;

    public MealServiceBean(MealRepository mealRepository, ProductRepository productRepository) {
        this.mealRepository = mealRepository;
        this.productRepository = productRepository;
    }
    
    public Meal getMealById(Integer mealId) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        if (meal.isPresent()) {
            return meal.get();
        } else {
            throw new NullPointerException("Could not find meal");
        }
    }

    public Meal saveMeal(Meal meal) {
        Optional<Product> byId = productRepository.findById(meal.getProduct().getId());
        return mealRepository.save(meal);
    }

}
