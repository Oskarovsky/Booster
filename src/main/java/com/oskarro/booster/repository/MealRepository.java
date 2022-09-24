package com.oskarro.booster.repository;

import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.model.Meal;
import com.oskarro.booster.model.Product;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends BaseRepository<Meal, Integer> {

    List<Meal> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Meal> findByDateTimeBetweenAndCustomerId(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer customerId);

    List<Meal> findByCustomerId(Integer customerId);

}
