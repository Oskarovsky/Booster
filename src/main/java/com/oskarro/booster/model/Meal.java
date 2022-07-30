package com.oskarro.booster.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meal implements Serializable, BaseEntity<Meal, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double portion;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Product product;

    @JsonProperty("productId")
    public void setProductById(Integer productId) {
        this.product = Product.fromId(productId);
    }

    @Override
    public void update(Meal source) {
        this.portion = source.getPortion();
        this.dateTime = source.getDateTime();
        this.product = source.getProduct();
    }

    @Override
    public Meal createNewInstance() {
        Meal newInstance = new Meal();
        newInstance.update(this);
        return newInstance;
    }
}
