package io.spd.csp.fieldmgmt.data.repo;

import io.spd.csp.fieldmgmt.data.entity.SiteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends ReactiveCrudRepository<SiteEntity, Integer> {}
