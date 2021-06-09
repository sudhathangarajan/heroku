package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.data.entity.PartEntity;
import io.spd.csp.fieldmgmt.data.entity.SiteEntity;
import io.spd.csp.fieldmgmt.data.repo.PartRepository;
import io.spd.csp.fieldmgmt.data.repo.SiteRepository;
import io.spd.csp.fieldmgmt.dto.PartDto;
import io.spd.csp.fieldmgmt.dto.SiteDto;
import io.spd.csp.fieldmgmt.mapper.PartMapperFactory;
import io.spd.csp.fieldmgmt.mapper.SiteMapperFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
record DataServiceImpl(SiteRepository siteRepo, SiteMapperFactory siteMapper,
                       PartRepository partRepo, PartMapperFactory partMapper) implements DataService {

    public Mono<Integer> addSite(SiteDto site) {
        return Mono.just(site).map(siteMapper.a2b()).flatMap(siteRepo::save)
                .map(SiteEntity::getId);
    }

    public Mono<Integer> addPart(PartDto part) {
        return Mono.just(part).map(partMapper.a2b()).flatMap(partRepo::save)
                .map(PartEntity::getId);
    }
}
