package io.spd.csp.fieldmgmt.dto;

import java.util.List;

public record InspectionDetailsDto(InspectionDto inspection, SiteDto site, List<ReservedPartDto> reservedParts,
                                   List<SitePartDto> siteParts) {}
