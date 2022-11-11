package com.oskarro.booster.repository;

import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.model.Provider;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends BaseRepository<Provider, Integer> {
}
