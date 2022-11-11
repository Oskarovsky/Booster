package com.oskarro.booster.repository;

import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.model.Product;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {

    Product findByName(@NotNull @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters") String name);

    List<Product> findAllByOrderByNameAsc();

    List<Product> findAllByProviderId(Integer providerId);

    List<Product> findAllByProviderName(String providerName);
}
