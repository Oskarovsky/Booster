package com.oskarro.booster.gateway;

import com.oskarro.booster.common.BaseGateway;
import com.oskarro.booster.common.BaseService;
import com.oskarro.booster.model.Provider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/provider")
public class ProviderGateway extends BaseGateway<Provider, Integer> {

    public ProviderGateway(BaseService<Provider, Integer> service) {
        super(service);
    }
}
