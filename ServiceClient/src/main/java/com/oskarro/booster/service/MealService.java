package com.oskarro.booster.service;

import com.oskarro.apiclient.model.Customer;
import com.oskarro.apiclient.model.Meal;
import com.oskarro.commons.common.BaseService;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService extends BaseService<Meal, Integer> {

    List<Meal> getMealFromPeriodOfTime(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Meal> getMealsByCustomerFromToday(Customer customer);

    List<Meal> getMealsByCustomer(Customer customer);
}
