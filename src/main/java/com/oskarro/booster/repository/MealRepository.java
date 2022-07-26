package com.oskarro.booster.repository;

import com.oskarro.booster.model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {

}
