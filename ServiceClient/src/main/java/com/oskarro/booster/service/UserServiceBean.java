package com.oskarro.booster.service;

import com.oskarro.apiclient.model.auth.UserMan;
import com.oskarro.commons.common.BaseRepository;
import com.oskarro.commons.common.BaseServiceBean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean extends BaseServiceBean<UserMan, Integer> implements UserService {

    public UserServiceBean(BaseRepository<UserMan, Integer> baseRepository) {
        super(baseRepository);
    }
}
