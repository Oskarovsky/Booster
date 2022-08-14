package com.oskarro.booster.model;

import com.oskarro.booster.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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
