package com.oskarro.booster.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public abstract class BaseGateway<T extends BaseEntity<T, K>, K> {

    private final BaseService<T, K> service;

    public BaseGateway(BaseService<T, K> service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> getPage(Pageable pageable){
        return new ResponseEntity<>(service.getPage(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable K id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<T> update(@RequestBody T updated, @PathVariable K id){
        return new ResponseEntity<>(service.update(id, updated), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created){
        T result = service.save(created);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        responseHeaders.setLocation(uri);

        return new ResponseEntity<>(result, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable K id){
        service.deleteById(id);
        return new ResponseEntity<>(String.format("Entity with id %s has been deleted", id), HttpStatus.OK);
    }

}
