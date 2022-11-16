package com.oskarro.booster.service;

import com.oskarro.apiclient.model.auth.UserMan;
import com.oskarro.commons.common.BaseService;

public interface UserService extends BaseService<UserMan, Integer> {

    UserMan getByUsername(String username);

}
