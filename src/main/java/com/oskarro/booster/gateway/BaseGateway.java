package com.oskarro.booster.gateway;

import com.oskarro.booster.model.BaseEntity;
import com.oskarro.booster.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseGateway<T extends BaseEntity<T, K>, K> {

    private final BaseService<T, K> service;

    public BaseGateway(BaseService<T, K> service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable K id){
        return ResponseEntity.ok(service.getById(id));
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
