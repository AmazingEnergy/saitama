package com.amazingenergy.saitamadomain.mapstruct;

import com.amazingenergy.core.domain.AuditSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface SimpleMapper {
    @Mappings({@Mapping(target = "destinationId", source = "source.id")})
    SimpleDestination sourceToDestination(SimpleSource source);

    @Mappings({@Mapping(target = "id", source = "destination.destinationId")})
    SimpleSource destinationToSource(SimpleDestination destination);
}
