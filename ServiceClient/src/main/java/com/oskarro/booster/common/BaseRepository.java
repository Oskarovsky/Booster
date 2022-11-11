package com.oskarro.booster.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<T, K>, K> extends JpaRepository<T, K> {


}
