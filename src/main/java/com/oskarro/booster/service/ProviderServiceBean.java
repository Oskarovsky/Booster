package com.oskarro.booster.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oskarro.booster.common.BaseRepository;
import com.oskarro.booster.common.BaseServiceBean;
import com.oskarro.booster.model.Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProviderServiceBean extends BaseServiceBean<Provider, Integer> implements ProviderService {

    public ProviderServiceBean(BaseRepository<Provider, Integer> baseRepository) {
        super(baseRepository);
    }

}
