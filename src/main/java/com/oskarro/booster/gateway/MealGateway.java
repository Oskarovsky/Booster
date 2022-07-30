package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.service.MealService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/meal")
public class MealGateway {

    private final MealService mealService;

    public MealGateway(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(value = "/{id}")
    public Meal getMealById(@PathVariable Integer id) {
        return mealService.getMealById(id);
    }

    @PostMapping("")
    public Meal saveMeal(@RequestBody Meal meal) {
        return mealService.saveMeal(meal);
    }

}
