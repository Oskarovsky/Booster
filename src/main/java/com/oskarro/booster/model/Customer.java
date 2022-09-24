package com.oskarro.booster.model;

public record Customer(Integer identifier,
                       String name,
                       DietType dietType,
                       double height,
                       double weight) {

}
