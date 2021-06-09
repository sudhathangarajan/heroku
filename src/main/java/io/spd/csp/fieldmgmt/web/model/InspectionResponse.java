package io.spd.csp.fieldmgmt.web.model;

import io.spd.csp.fieldmgmt.dto.InspectionDetailsDto;

public class InspectionResponse extends BaseResponse<InspectionDetailsDto> {

    public static InspectionResponse success(InspectionDetailsDto details) {
        return new InspectionResponse("200", Status.success, details);
    }

    private InspectionResponse(String code, Status status, InspectionDetailsDto data) {
        super(code, status, data);
    }
}
