package com.oskarro.commons.common;

import com.oskarro.commons.util.MappingUtils;
import org.modelmapper.ModelMapper;

public interface BaseEntityDto {

    default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
        return mapper;
    }
}
