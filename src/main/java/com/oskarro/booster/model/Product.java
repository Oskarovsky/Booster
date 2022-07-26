package com.oskarro.booster.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer energy;

    private Double protein;

    private Double fat;

    private Double carbs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Meal> meal;

}
