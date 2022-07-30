package com.oskarro.booster.service;

import com.oskarro.booster.model.BaseEntity;
import com.oskarro.booster.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

public abstract class BaseService<T extends BaseEntity<T>> {

    private final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T get(Integer id){
        return repository
                .findById(id)
                .orElseThrow(NullPointerException::new);
    }

    @Transactional
    public T update(T updated){
        T dbDomain = get(updated.getId());
        dbDomain.update(updated);
        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(Integer id){
        //check if object with this id exists
        get(id);
        repository.deleteById(id);
    }
}
