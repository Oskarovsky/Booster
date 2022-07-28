package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.service.MealService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/meal")
public class MealGateway {

    private final MealService mealService;

    public MealGateway(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("")
    public Meal saveMeal(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

}
