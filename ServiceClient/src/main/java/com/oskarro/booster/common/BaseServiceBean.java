package com.oskarro.booster.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BaseServiceBean<T extends BaseEntity<T, K>, K> implements BaseService<T, K> {

    protected BaseRepository<T, K> baseRepository;

    ObjectMapper mapper = new ObjectMapper();

    public BaseServiceBean(BaseRepository<T, K> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<T> get() {
        return baseRepository.findAll();
    }

    @Override
    @Transactional
    public T getById(K id) {
        try {
            return baseRepository.findById(id).orElseThrow(NullPointerException::new);
        } catch (NullPointerException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find entity with id " + id);
        }
    }

    @Override
    @Transactional
    public T save(T newDomain) {
        T dbDomain = newDomain.createNewInstance();
        return baseRepository.save(dbDomain);
    }

    @Override
    @Transactional
    public Iterable<T> saveAll(List<T> entities) {
        return baseRepository.saveAll(entities);
    }

    @Override
    @Transactional
    public T update(K id, T updated) {
        if (id != updated.getId()) {
            throw new IllegalArgumentException("Entity identifier is not valid");
        }
        T dbDomain = getById(updated.getId());
        dbDomain.update(updated);
        return baseRepository.save(dbDomain);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(K id) {
        //check if object with this id exists
        getById(id);
        baseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Page<T> getPage(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void loadDataFromJsonToDatabase(Resource resource, TypeReference<List<T>> typeReference) {
        try (InputStream inputStream = resource.getInputStream()) {
            List<T> entities = mapper.readValue(inputStream, typeReference);
            saveAll(entities);
        } catch (IOException e) {
            System.out.printf("Could not save entity in database: %s", e);
            throw new RuntimeException(e);
        }
    }
}
