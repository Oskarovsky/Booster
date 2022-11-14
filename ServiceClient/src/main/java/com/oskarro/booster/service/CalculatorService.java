package com.oskarro.booster.service;

import com.oskarro.apiclient.model.Customer;
import com.oskarro.apiclient.model.Meal;
import com.oskarro.booster.dto.CounterDto;

import java.util.List;

public interface CalculatorService {

    CounterDto calculateTheNutrients(List<Meal> meals);

    CounterDto calculateDietForCustomer(Customer customer);

    CounterDto calculateTheNutrientsFromToday(Customer customer);
}
