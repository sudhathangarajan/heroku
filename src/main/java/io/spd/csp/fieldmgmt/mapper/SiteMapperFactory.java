package io.spd.csp.fieldmgmt.mapper;

import io.spd.csp.fieldmgmt.data.entity.SiteEntity;
import io.spd.csp.fieldmgmt.dto.SiteDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SiteMapperFactory implements MapperFactory<SiteDto, SiteEntity> {

    @Override
    public Function<SiteDto, SiteEntity> a2b() {
        return dto -> new SiteEntity(dto.id(), dto.postalCode(), dto.description());
    }

    @Override
    public Function<SiteEntity, SiteDto> b2a() {
        return ent -> new SiteDto(ent.getId(), ent.getPostalCode(), ent.getDescription());
    }
}
