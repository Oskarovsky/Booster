package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseServiceBean;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
}
