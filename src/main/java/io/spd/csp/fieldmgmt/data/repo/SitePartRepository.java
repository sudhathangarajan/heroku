package io.spd.csp.fieldmgmt.data.repo;

import io.spd.csp.fieldmgmt.data.entity.SitePartEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SitePartRepository extends ReactiveCrudRepository<SitePartEntity, Integer> {

    Flux<SitePartEntity> findBySiteId(Integer siteId);
}
