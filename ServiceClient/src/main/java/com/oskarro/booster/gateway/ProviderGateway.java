package com.oskarro.booster.gateway;

import com.oskarro.apiclient.model.Provider;
import com.oskarro.commons.common.BaseGateway;
import com.oskarro.commons.common.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/provider")
public class ProviderGateway extends BaseGateway<Provider, Integer> {

    public ProviderGateway(BaseService<Provider, Integer> service) {
        super(service);
    }
}
