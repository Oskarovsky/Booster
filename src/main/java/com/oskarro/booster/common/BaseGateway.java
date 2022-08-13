package com.oskarro.booster.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public abstract class BaseGateway<T extends BaseEntity<T, K>, K> {

    private final BaseService<T, K> service;

    public BaseGateway(BaseService<T, K> service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> getPage(Pageable pageable){
        try {
            return ResponseEntity.ok(service.getPage(pageable));
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable K id){
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (NullPointerException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable K id){
        service.deleteById(id);
        return ResponseEntity.ok("Ok");
    }

}
