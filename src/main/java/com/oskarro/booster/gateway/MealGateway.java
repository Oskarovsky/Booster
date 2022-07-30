package com.oskarro.booster.gateway;

import com.oskarro.booster.model.Meal;
import com.oskarro.booster.repository.BaseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/meal")
public class MealGateway extends BaseGateway<Meal> {

    public MealGateway(BaseRepository<Meal> repository) {
        super(repository);
    }
}
