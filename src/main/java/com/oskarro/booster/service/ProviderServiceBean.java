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

    @Value("classpath:data/data-provider.json")
    Resource resourceFile;

    public ProviderServiceBean(BaseRepository<Provider, Integer> baseRepository) {
        super(baseRepository);
    }

    public void loadDataFromJsonToDatabase(final String jsonFile) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Provider>> typeReference = new TypeReference<List<Provider>>(){};
        try (InputStream inputStream = resourceFile.getInputStream()) {
            List<Provider> providers = mapper.readValue(inputStream, typeReference);
            saveAll(providers);
            System.out.println("Provider has been saved");
        } catch (IOException e) {
            System.out.println("Could not save providers to database from json file: " + e);
        }
    }
}
