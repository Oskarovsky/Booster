package com.oskarro.booster.dto;

import com.oskarro.apiclient.model.Meal;
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

    private Integer energy = 0;

    private Double protein = 0d;

    private Double fat = 0d;

    private Double carbs = 0d;

    private List<Meal> meals = new ArrayList<>();

}
