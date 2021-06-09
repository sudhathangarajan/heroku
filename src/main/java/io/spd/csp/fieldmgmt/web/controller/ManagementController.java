package io.spd.csp.fieldmgmt.web.controller;

import io.spd.csp.fieldmgmt.Constants;
import io.spd.csp.fieldmgmt.mapper.InspectionMapperFactory;
import io.spd.csp.fieldmgmt.service.FieldService;
import io.spd.csp.fieldmgmt.web.model.InspectionsResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping(Constants.Web.MANAGER_PATH)
public record ManagementController(FieldService fieldService, InspectionMapperFactory inspectionMapper) {

    @RequestMapping("/inspections")
    public Mono<InspectionsResponse> getAllInspectionsForToday() {
        return fieldService.getAllInspectionsForToday(LocalDate.now()).collectList()
                .map(InspectionsResponse::success);
    }
}
