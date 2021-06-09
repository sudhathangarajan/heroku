package io.spd.csp.fieldmgmt.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SparePartDto {

    Integer partId;

    String partName;

    Integer quantity;
}
