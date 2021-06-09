package io.spd.csp.fieldmgmt.data.repo;

import io.spd.csp.fieldmgmt.data.entity.SparePartEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SparePartRepository extends ReactiveCrudRepository<SparePartEntity, Integer> {

    Mono<SparePartEntity> findByPartId(Integer partId);
}
