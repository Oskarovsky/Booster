package com.oskarro.booster.service;

import com.oskarro.apiclient.model.auth.Role;
import com.oskarro.commons.common.BaseRepository;
import com.oskarro.commons.common.BaseServiceBean;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceBean extends BaseServiceBean<Role, Integer> implements RoleService {

    public RoleServiceBean(BaseRepository<Role, Integer> baseRepository) {
        super(baseRepository);
    }
}
