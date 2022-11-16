package com.oskarro.booster.service;

import com.oskarro.apiclient.model.auth.UserMan;
import com.oskarro.booster.repository.UserRepository;
import com.oskarro.commons.common.BaseRepository;
import com.oskarro.commons.common.BaseServiceBean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean extends BaseServiceBean<UserMan, Integer> implements UserService {

    UserRepository userRepository;

    public UserServiceBean(BaseRepository<UserMan, Integer> baseRepository, UserRepository userRepository) {
        super(baseRepository);
        this.userRepository = userRepository;
    }

    public UserMan getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
