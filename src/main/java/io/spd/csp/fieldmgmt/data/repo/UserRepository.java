package io.spd.csp.fieldmgmt.data.repo;

import io.spd.csp.fieldmgmt.data.entity.UserEntity;
import io.spd.csp.fieldmgmt.dto.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {

    Mono<UserEntity> findByUsername(String username);

    Mono<UserEntity> findByCurrentSessionId(String currentSessionId);

    Flux<UserEntity> findByRole(Role rolename);
}
