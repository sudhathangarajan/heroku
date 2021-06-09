package io.spd.csp.fieldmgmt.data.entity;

import io.spd.csp.fieldmgmt.dto.InspectionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("inspections")
public class InspectionEntity {

    @Id
    private Integer id;

    private Integer siteId;

    private LocalDateTime scheduledAt;

    private LocalDateTime completedAt;

    private InspectionDto.Status status;

    private String remarks;

    private Integer technicianId;

    private Integer previousInspectionId;
}
