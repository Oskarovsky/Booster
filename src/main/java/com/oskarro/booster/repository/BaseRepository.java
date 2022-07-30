package com.oskarro.booster.repository;

import com.oskarro.booster.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<T>> extends JpaRepository<T, Integer> {



}
