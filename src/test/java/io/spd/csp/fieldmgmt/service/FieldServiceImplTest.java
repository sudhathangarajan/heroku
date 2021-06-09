package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.data.entity.InspectionEntity;
import io.spd.csp.fieldmgmt.data.entity.UserEntity;
import io.spd.csp.fieldmgmt.data.repo.InspectionRepository;
import io.spd.csp.fieldmgmt.data.repo.UserRepository;
import io.spd.csp.fieldmgmt.dto.InspectionDto;
import io.spd.csp.fieldmgmt.mapper.InspectionMapperFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { FieldServiceImpl.class, InspectionMapperFactory.class })
class FieldServiceImplTest {

    @MockBean
    private InspectionRepository inspRepo;

    @MockBean
    private UserRepository userRepo;

    @Autowired
    private FieldService service;

    @Test
    void shouldGetInspectionsSuccessfully() {
        // given: user
        var username = randomAlphanumeric(20);
        var userId = new Random().nextInt();
        var user = mock(UserEntity.class);
        when(user.getId()).thenReturn(userId);
        when(userRepo.findByUsername(username)).thenReturn(Mono.just(user));

        // given: Inspections
        List<InspectionEntity> inspections = List.of(createInspection(userId), createInspection(userId));
        when(inspRepo.findScheduledInspections(eq(userId), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Flux.fromIterable(inspections));

        // when
        LocalDate currentDate = LocalDate.now();
        StepVerifier.FirstStep<InspectionDto> verifier = StepVerifier.create(service.getInspections(username, currentDate));

        // then
        verifier.consumeNextWith(result -> {
            assertEquals(userId, result.technicianId());
        }).expectNextCount(1).then(() -> {
            verify(inspRepo, atLeastOnce()).findScheduledInspections(eq(userId),
                    argThat(dt -> dt.isEqual(currentDate.atStartOfDay())),
                    argThat(dt -> dt.isEqual(currentDate.atTime(LocalTime.MAX)))
            );
            verify(userRepo, atLeastOnce()).findByUsername(username);
        }).verifyComplete();
    }

    @Test
    void shouldUpdateInspection() {
        // given: Inspection
        var inspectionId = new Random().nextInt();
        InspectionEntity inspection = new InspectionEntity();
        inspection.setId(inspectionId);
        when(inspRepo.findById(inspectionId)).thenReturn(Mono.just(inspection));
        when(inspRepo.save(any(InspectionEntity.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        // given: updates
        InspectionDto.Status updatedStatus = InspectionDto.Status.COMPLETED;
        var updatedRemarks = randomAlphanumeric(20);

        // when
        StepVerifier.FirstStep<Boolean> verifier = StepVerifier.create(service.updateInspection(inspectionId,
                updatedStatus, updatedRemarks));

        // then
        verifier.consumeNextWith(res -> {
            assertTrue(res);
            verify(inspRepo, atLeastOnce()).save(argThat(ins -> ins.getId().equals(inspectionId)
                    && ins.getStatus() == updatedStatus && ins.getRemarks().equals(updatedRemarks)));
        }).verifyComplete();
    }

    private InspectionEntity createInspection(Integer technicianId) {
        return InspectionEntity.builder().technicianId(technicianId).build();
    }
}