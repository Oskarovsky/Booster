package com.oskarro.booster.service;

import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.common.BaseServiceBean;
import com.oskarro.booster.model.Provider;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceBean extends BaseServiceBean<Provider, Integer> implements ProviderService {

    public ProviderServiceBean(BaseRepository<Provider, Integer> baseRepository) {
        super(baseRepository);
    }
}
