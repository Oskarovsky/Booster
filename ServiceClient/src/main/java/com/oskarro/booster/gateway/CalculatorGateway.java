package com.oskarro.booster.gateway;

import com.oskarro.booster.dto.CounterDto;
import com.oskarro.booster.model.Customer;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.service.CalculatorService;
import com.oskarro.booster.service.MealService;
import com.oskarro.booster.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/calc")
public class CalculatorGateway {

    private final MealService mealService;
    private final ProductService productService;

    private final CalculatorService calculatorService;


    public CalculatorGateway(final MealService mealService,
                             final ProductService productService,
                             final CalculatorService calculatorService) {
        this.mealService = mealService;
        this.productService = productService;
        this.calculatorService = calculatorService;
    }

    @GetMapping("/consumedIngredients")
    public ResponseEntity<CounterDto> calculateConsumedIngredients(@RequestParam String startDate, @RequestParam String endDate) {
        List<Meal> meals = mealService.getMealFromPeriodOfTime(
                LocalDateTime.parse(startDate),
                LocalDateTime.parse(endDate)
        );
        CounterDto counterDto = calculatorService.calculateTheNutrients(meals);
        counterDto.setStartDate(LocalDateTime.parse(startDate));
        counterDto.setEndDate(LocalDateTime.parse(endDate));
        return new ResponseEntity<>(counterDto, HttpStatus.OK);
    }

    @PostMapping("/calculateTheNutrientsFromToday")
    public ResponseEntity<CounterDto> calculateTheNutrientsFromToday(@RequestBody Customer customer) {
        return new ResponseEntity<>(calculatorService.calculateTheNutrientsFromToday(customer), HttpStatus.OK);
    }



}
