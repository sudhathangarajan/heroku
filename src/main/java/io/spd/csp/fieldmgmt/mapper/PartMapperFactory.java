package io.spd.csp.fieldmgmt.mapper;

import io.spd.csp.fieldmgmt.data.entity.PartEntity;
import io.spd.csp.fieldmgmt.dto.PartDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PartMapperFactory implements MapperFactory<PartDto, PartEntity> {

    @Override
    public Function<PartDto, PartEntity> a2b() {
        return dto -> new PartEntity(dto.id(), dto.name(), dto.available());
    }

    @Override
    public Function<PartEntity, PartDto> b2a() {
        return ent -> new PartDto(ent.getId(), ent.getName(), ent.getAvailable());
    }
}
