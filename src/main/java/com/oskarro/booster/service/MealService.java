package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseService;
import com.oskarro.booster.model.Customer;
import com.oskarro.booster.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService extends BaseService<Meal, Integer> {

    List<Meal> getMealFromPeriodOfTime(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Meal> getMealsByCustomerFromToday(Customer customer);

    List<Meal> getMealsByCustomer(Customer customer);
}
