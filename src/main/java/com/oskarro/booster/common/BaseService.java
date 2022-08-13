package com.oskarro.booster.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T extends BaseEntity<T, K>, K> {

    List<T> get();
    T create(T entity);
    T update (T entity);
    T getById(K id);
    void delete(T entity);
    void deleteById(K id);
    Page<T> getPage(Pageable pageable);
    long count();
}
