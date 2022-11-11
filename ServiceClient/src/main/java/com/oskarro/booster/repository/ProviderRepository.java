package com.oskarro.booster.repository;

import com.oskarro.apiclient.model.Provider;
import com.oskarro.commons.common.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends BaseRepository<Provider, Integer> {
}
