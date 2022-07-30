package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/meal")
public class MealGateway {

    private final MealService mealService;

    public MealGateway(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Integer id) {
        return new ResponseEntity<>(mealService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Meal>> getAllMeals() {


        re
    }

    @PostMapping("")
    public Meal saveMeal(@RequestBody Meal meal) {
        return mealService.save(meal);
    }

}
