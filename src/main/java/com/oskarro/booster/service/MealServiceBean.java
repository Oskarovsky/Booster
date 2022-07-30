package com.oskarro.booster.service;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class MealServiceBean extends BaseServiceBean<Meal, Integer> implements MealService {

    public MealServiceBean(BaseRepository<Meal, Integer> baseRepository) {
        super(baseRepository);
    }
}
