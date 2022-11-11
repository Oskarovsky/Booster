package com.oskarro.booster.common;

public interface BaseEntity<T, K> {

    K getId();

    // update current instance with provided data
    void update(T source);

    // based on current data create new instance with new id
    T createNewInstance();
}
