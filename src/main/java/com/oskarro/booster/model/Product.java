package com.oskarro.booster.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters")
    private String name;

    private Integer energy;

    private Double protein;

    private Double fat;

    private Double carbs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Meal> meals = new ArrayList<>();

    public void addMeal(Meal meal) {
        if (meal == null) {
            throw new NullPointerException("Could not add null Meal");
        }

        if (meal.getProduct() != null) {
            throw new IllegalStateException("Meal is already assigned to an Product");
        }
        meal.setProduct(this);
        meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
        meal.setProduct(this);
    }

}
