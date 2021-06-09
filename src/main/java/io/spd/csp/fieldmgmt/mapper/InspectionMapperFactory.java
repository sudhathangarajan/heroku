package io.spd.csp.fieldmgmt.mapper;

import io.spd.csp.fieldmgmt.data.entity.InspectionEntity;
import io.spd.csp.fieldmgmt.dto.InspectionDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InspectionMapperFactory implements MapperFactory<InspectionDto, InspectionEntity> {

    @Override
    public Function<InspectionDto, InspectionEntity> a2b() {
        return dto -> new InspectionEntity(dto.id(), dto.siteId(), dto.scheduledAt(), dto.completedAt(), dto.status(),
                dto.remarks(), dto.technicianId(), dto.previousInspectionId());
    }

    @Override
    public Function<InspectionEntity, InspectionDto> b2a() {
        return ent -> new InspectionDto(ent.getId(), ent.getSiteId(), ent.getScheduledAt(), ent.getCompletedAt(),
                ent.getStatus(), ent.getRemarks(), ent.getTechnicianId(), ent.getPreviousInspectionId());
    }
}
