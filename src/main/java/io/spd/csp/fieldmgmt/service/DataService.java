package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.dto.PartDto;
import io.spd.csp.fieldmgmt.dto.SiteDto;
import reactor.core.publisher.Mono;

public interface DataService {

    Mono<Integer> addSite(SiteDto site);

    Mono<Integer> addPart(PartDto part);
}
