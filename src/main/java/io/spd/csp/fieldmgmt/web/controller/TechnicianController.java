package io.spd.csp.fieldmgmt.web.controller;

import io.spd.csp.fieldmgmt.Constants;
import io.spd.csp.fieldmgmt.dto.InspectionDto;
import io.spd.csp.fieldmgmt.dto.PartDto;
import io.spd.csp.fieldmgmt.service.FieldService;
import io.spd.csp.fieldmgmt.web.model.InspectionResponse;
import io.spd.csp.fieldmgmt.web.model.InspectionUpdateRequest;
import io.spd.csp.fieldmgmt.web.model.InspectionsResponse;
import io.spd.csp.fieldmgmt.web.model.SparePartsResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@RestController
@RequestMapping(Constants.Web.TECHNICIAN_PATH)
public record TechnicianController(FieldService fieldService) {

    @GetMapping({ "/inspections", "/inspections/{scheduledAt:\\d{4}-\\d{2}-\\d{2}}" })
    public Mono<InspectionsResponse> getInspectionsToday(@PathVariable(required = false)
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                         Optional<LocalDate> scheduledAt) {
        return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal).cast(UserDetails.class).map(UserDetails::getUsername)
                .flatMapMany(username -> fieldService.getInspections(username, scheduledAt.orElse(LocalDate.now())))
                .collectList().doOnNext(inspections -> inspections.sort(Comparator.comparing(InspectionDto::scheduledAt)))
                .map(InspectionsResponse::success);
    }

    @GetMapping("/inspections/{inspectionId:\\d}")
    public Mono<InspectionResponse> getInspectionDetails(@PathVariable Integer inspectionId) {
        return fieldService.getInspectionDetails(inspectionId).map(InspectionResponse::success);
    }

    @GetMapping("/spare-parts")
    public Mono<SparePartsResponse> getSpareParts() {
        return fieldService.getSpareParts().collectList().map(SparePartsResponse::success);
    }

    @PutMapping("/inspection/{id}")
    public Mono<Boolean> updateInspection(@PathVariable Integer id, @RequestBody InspectionUpdateRequest request) {
        return fieldService.updateInspection(id, request.getStatus(), request.getRemarks());
    }
}
