package io.spd.csp.fieldmgmt.data.repo;

import io.spd.csp.fieldmgmt.data.entity.ReservationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<ReservationEntity, Integer> {

    Flux<ReservationEntity> findByInspectionId(Integer inspectionId);
}
