package io.spd.csp.fieldmgmt.data.repo;

import io.spd.csp.fieldmgmt.data.entity.InspectionEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface InspectionRepository extends ReactiveCrudRepository<InspectionEntity, Integer> {

    @Query("SELECT * FROM inspections WHERE technician_id = :technicianId AND scheduled_at BETWEEN :fromScheduledAt AND :toScheduledAt")
    Flux<InspectionEntity> findScheduledInspections(Integer technicianId, LocalDateTime fromScheduledAt,
                                                                   LocalDateTime toScheduledAt);
}
