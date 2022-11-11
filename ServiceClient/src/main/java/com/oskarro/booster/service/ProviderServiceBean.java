package com.oskarro.booster.service;

import com.oskarro.apiclient.model.Provider;
import com.oskarro.commons.common.BaseRepository;
import com.oskarro.commons.common.BaseServiceBean;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceBean extends BaseServiceBean<Provider, Integer> implements ProviderService {

    public ProviderServiceBean(BaseRepository<Provider, Integer> baseRepository) {
        super(baseRepository);
    }

}
