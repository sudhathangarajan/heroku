package io.spd.csp.fieldmgmt.web.model;

import io.spd.csp.fieldmgmt.dto.InspectionDto;

import java.util.List;

public class InspectionsResponse extends BaseResponse<List<InspectionDto>> {

    public static InspectionsResponse success(List<InspectionDto> inspections) {
        return new InspectionsResponse("200", Status.success, inspections);
    }

    private InspectionsResponse(String code, Status status, List<InspectionDto> data) {
        super(code, status, data);
    }
}
