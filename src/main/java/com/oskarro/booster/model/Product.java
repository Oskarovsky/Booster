package com.oskarro.booster.model;

import com.fasterxml.jackson.annotation.*;
import com.oskarro.booster.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIdentityReference(alwaysAsId = true)
    private Provider provider;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
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

    @JsonProperty("providerId")
    public void setProviderById(Integer providerId) {
        this.provider = Provider.fromId(providerId);
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
        this.provider = source.getProvider();
        this.price = source.getPrice();
    }

    @Override
    public Product createNewInstance() {
        Product newInstance = new Product();
        newInstance.update(this);
        return newInstance;
    }
}
