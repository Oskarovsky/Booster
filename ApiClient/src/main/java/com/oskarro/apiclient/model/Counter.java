package com.oskarro.apiclient.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "counter")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Counter implements Serializable, BaseEntity<Counter, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer energy;

    private Double protein;

    private Double fat;

    private Double carbs;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Override
    public void update(Counter source) {
        // TODO
    }

    @Override
    public Counter createNewInstance() {
        Counter newInstance = new Counter();
        newInstance.update(this);
        return newInstance;
    }
}
