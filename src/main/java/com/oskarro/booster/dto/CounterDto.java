package com.oskarro.booster.dto;

import com.oskarro.booster.model.Meal;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CounterDto {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer energy;

    private Double protein;

    private Double fat;

    private Double carbs;

    private List<Meal> meals = new ArrayList<>();

}
