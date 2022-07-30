package com.oskarro.booster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable, BaseEntity<Product, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public static Product fromId(Integer productId) {
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    @Override
    public void update(Product source) {
        this.fat = source.getFat();
        this.carbs = source.getCarbs();
        this.energy = source.getEnergy();
        this.name = source.getName();
        this.protein = source.getProtein();
        this.meals = source.getMeals();
    }

    @Override
    public Product createNewInstance() {
        Product newInstance = new Product();
        newInstance.update(this);
        return newInstance;
    }
}
