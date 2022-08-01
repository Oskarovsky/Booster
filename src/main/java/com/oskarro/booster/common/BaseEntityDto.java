package com.oskarro.booster.common;

import com.oskarro.booster.util.MappingUtils;
import org.modelmapper.ModelMapper;

public interface BaseEntityDto {

    default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
        return mapper;
    }
}
