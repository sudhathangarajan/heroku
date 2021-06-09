package io.spd.csp.fieldmgmt.web.model;

import io.spd.csp.fieldmgmt.dto.InspectionDto;
import lombok.Data;

@Data
public class InspectionUpdateRequest {

    private InspectionDto.Status status;

    private String remarks;
}
