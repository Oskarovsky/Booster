package com.oskarro.booster.model;

public interface BaseEntity<T> {

    Integer getId();

    // update current instance with provided data
    void update(T source);

    // based on current data create new instance with new id
    T createNewInstance();
}
