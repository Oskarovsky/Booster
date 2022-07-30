package com.oskarro.booster.service;

import com.oskarro.booster.model.BaseEntity;
import com.oskarro.booster.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BaseServiceBean<T extends BaseEntity<T, K>, K> implements BaseService<T, K> {

    protected BaseRepository<T, K> baseRepository;

    public BaseServiceBean(BaseRepository<T, K> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> get() {
        return baseRepository.findAll();
    }

    @Override
    public T getById(K id) {
        return baseRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public T create(T newDomain) {
        T dbDomain = newDomain.createNewInstance();
        return baseRepository.save(dbDomain);
    }

    @Override
    public T update(T updated) {
        T dbDomain = getById(updated.getId());
        dbDomain.update(updated);
        return baseRepository.save(dbDomain);
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteById(K id) {
        //check if object with this id exists
        getById(id);
        baseRepository.deleteById(id);
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return 0;
    }
}
