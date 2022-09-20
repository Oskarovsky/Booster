package com.oskarro.booster.service;

import com.oskarro.booster.dto.CounterDto;
import com.oskarro.booster.model.Customer;
import com.oskarro.booster.model.Meal;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CalculatorService {

    CounterDto calculateTheNutrients(List<Meal> meals);

    CounterDto calculateCompositionOfNutrients(Customer customer);
}
