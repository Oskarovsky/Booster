package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseServiceBean;
import com.oskarro.booster.model.Customer;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceBean extends BaseServiceBean<Meal, Integer> implements MealService {

    private final MealRepository mealRepository;

    public MealServiceBean(MealRepository mealRepository) {
        super(mealRepository);
        this.mealRepository = mealRepository;
    }

    public List<Meal> getMealFromPeriodOfTime(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
        return mealRepository.findByDateTimeBetween(startDateTime, endDateTime);
    }

    public List<Meal> getMealsByCustomerFromToday(Customer customer) {
        return mealRepository.findByDateTimeBetweenAndCustomerId(
                LocalDate.now().atTime(LocalTime.MIN),
                LocalDate.now().atTime(LocalTime.MAX),
                customer.identifier()
        );
    }

    public List<Meal> getMealsByCustomer(final Customer customer) {
        return mealRepository.findByCustomerId(customer.identifier());
    }
}
