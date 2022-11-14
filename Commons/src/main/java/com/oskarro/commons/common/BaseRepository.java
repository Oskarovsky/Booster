package com.oskarro.commons.common;

import com.oskarro.apiclient.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<T, K>, K> extends JpaRepository<T, K> {


}
