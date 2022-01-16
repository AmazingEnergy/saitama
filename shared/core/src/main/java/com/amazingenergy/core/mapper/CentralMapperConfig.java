package com.amazingenergy.core.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;

@MapperConfig(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface CentralMapperConfig {
}
