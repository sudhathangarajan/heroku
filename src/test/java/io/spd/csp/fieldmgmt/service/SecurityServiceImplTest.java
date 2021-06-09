package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.data.entity.UserEntity;
import io.spd.csp.fieldmgmt.data.repo.UserRepository;
import io.spd.csp.fieldmgmt.dto.Role;
import io.spd.csp.fieldmgmt.dto.UserDto;
import io.spd.csp.fieldmgmt.mapper.UserMapperFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { SecurityServiceImpl.class, UserMapperFactory.class })
class SecurityServiceImplTest {

    @MockBean
    private UserRepository userRepo;

    @Autowired
    private SecurityService service;

    @Test
    void shouldLoadAndMapUser() {
        // given: current session id
        var currentSessionId = randomAlphanumeric(20);

        // given: UserEntity
        var userEntity = new UserEntity(new Random().nextInt(), randomAlphanumeric(10), randomAlphanumeric(10),
                currentSessionId, Role.TECHNICIAN);

        // given: repository
        when(userRepo.findByCurrentSessionId(currentSessionId)).thenReturn(Mono.just(userEntity));

        // when
        StepVerifier.FirstStep<UserDto> verifier = StepVerifier.create(service.getUserBySessionId(currentSessionId));

        // then
        verifier.consumeNextWith(u -> {
            assertEquals(currentSessionId, u.currentSessionId());
            verify(userRepo, atLeastOnce()).findByCurrentSessionId(eq(currentSessionId));
        }).verifyComplete();
    }
}