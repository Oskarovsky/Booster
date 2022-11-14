package com.oskarro.booster.repository;

import com.oskarro.apiclient.model.auth.UserMan;
import com.oskarro.commons.common.BaseRepository;

public interface UserRepository extends BaseRepository<UserMan, Integer> {

    UserMan findByUsername(String username);
}
