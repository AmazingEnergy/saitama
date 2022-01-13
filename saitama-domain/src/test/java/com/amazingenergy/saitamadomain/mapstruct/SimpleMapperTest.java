package com.amazingenergy.saitamadomain.mapstruct;

import com.amazingenergy.core.domain.AuditSection;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SimpleMapperTest {
    private SimpleMapper mapper
            = Mappers.getMapper(SimpleMapper.class);

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        var auditSection = new AuditSection(new Date(), "System", new Date(), "System");
        var auditSectionList = List.of(
                new AuditSection(new Date(), "System", new Date(), "System"),
                new AuditSection(new Date(), "System", new Date(), "System"),
                new AuditSection(new Date(), "System", new Date(), "System")
        );
        var source = new SimpleSource(UUID.randomUUID(), "SourceName", "SourceDescription", auditSection, auditSectionList);
        var destination = mapper.sourceToDestination(source);

        assertEquals(source.getId(), destination.getDestinationId());
        assertEquals(source.getName(), destination.getName());
        assertEquals(source.getDescription(), destination.getDescription());
        assertEquals(source.getAuditSection().getCreatedBy(), destination.getAuditSection().getCreatedBy());
        assertEquals(source.getAuditSection().getModifiedBy(), destination.getAuditSection().getModifiedBy());
        assertEquals(source.getAuditSection().getDateCreated(), destination.getAuditSection().getDateCreated());
        assertEquals(source.getAuditSection().getDateModified(), destination.getAuditSection().getDateModified());
        assertEquals(source.getAuditSectionList().size(), destination.getAuditSectionList().size());
    }

    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        var auditSection = new EmbeddableAuditSection(new Date(), "System", new Date(), "System");
        var auditSectionList = List.of(
                new EmbeddableAuditSection(new Date(), "System", new Date(), "System"),
                new EmbeddableAuditSection(new Date(), "System", new Date(), "System"),
                new EmbeddableAuditSection(new Date(), "System", new Date(), "System")
        );
        var destination = new SimpleDestination(UUID.randomUUID(), "DestinationName", "DestinationDescription", auditSection, auditSectionList);
        var source = mapper.destinationToSource(destination);

        assertEquals(source.getId(), destination.getDestinationId());
        assertEquals(destination.getName(), source.getName());
        assertEquals(destination.getDescription(), source.getDescription());
        assertEquals(source.getAuditSection().getCreatedBy(), destination.getAuditSection().getCreatedBy());
        assertEquals(source.getAuditSection().getModifiedBy(), destination.getAuditSection().getModifiedBy());
        assertEquals(source.getAuditSection().getDateCreated(), destination.getAuditSection().getDateCreated());
        assertEquals(source.getAuditSection().getDateModified(), destination.getAuditSection().getDateModified());
        assertEquals(source.getAuditSectionList().size(), destination.getAuditSectionList().size());
    }
}
