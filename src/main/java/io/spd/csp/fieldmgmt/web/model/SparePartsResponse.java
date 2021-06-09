package io.spd.csp.fieldmgmt.web.model;

import io.spd.csp.fieldmgmt.dto.SparePartDto;

import java.util.List;

public class SparePartsResponse extends BaseResponse<List<SparePartDto>> {

    public static SparePartsResponse success(List<SparePartDto> parts) {
        return new SparePartsResponse("200", Status.success, parts);
    }

    private SparePartsResponse(String code, Status status, List<SparePartDto> data) {
        super(code, status, data);
    }
}
