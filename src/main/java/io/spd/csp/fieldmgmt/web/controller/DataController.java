package io.spd.csp.fieldmgmt.web.controller;

import io.spd.csp.fieldmgmt.service.DataService;
import io.spd.csp.fieldmgmt.web.model.PartRequest;
import io.spd.csp.fieldmgmt.web.model.SiteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @PostMapping("/sites")
    public Mono<Integer> addSite(SiteRequest request) {
        return dataService.addSite(request.getSite());
    }

    @PostMapping("/parts")
    public Mono<Integer> addPart(PartRequest request) {
        return dataService.addPart(request.getPart());
    }
}
