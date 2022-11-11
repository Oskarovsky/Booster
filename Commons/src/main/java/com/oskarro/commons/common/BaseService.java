package com.oskarro.commons.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oskarro.apiclient.model.BaseEntity;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T extends BaseEntity<T, K>, K> {

    List<T> get();
    T save(T entity);
    Iterable<T> saveAll(List<T> entities);
    T update (K id, T entity);
    T getById(K id);
    void delete(T entity);
    void deleteById(K id);
    Page<T> getPage(Pageable pageable);
    long count();
    void loadDataFromJsonToDatabase(Resource resource, TypeReference<List<T>> typeReference);
}
