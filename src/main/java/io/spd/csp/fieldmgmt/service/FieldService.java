package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.dto.InspectionDetailsDto;
import io.spd.csp.fieldmgmt.dto.InspectionDto;
import io.spd.csp.fieldmgmt.dto.SparePartDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface FieldService {

    Flux<InspectionDto> getInspections(String username, LocalDate localDate);

    Flux<InspectionDto> getAllInspectionsForToday(LocalDate localDate);

    Mono<InspectionDetailsDto> getInspectionDetails(Integer inspectionId);

    Flux<SparePartDto> getSpareParts();

    Mono<Boolean> updateInspection(Integer inspectionId, InspectionDto.Status status, String remarks);
}
