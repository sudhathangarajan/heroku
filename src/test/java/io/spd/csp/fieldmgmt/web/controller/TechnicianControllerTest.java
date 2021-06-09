package io.spd.csp.fieldmgmt.web.controller;

import io.spd.csp.fieldmgmt.TestDateUtil;
import io.spd.csp.fieldmgmt.dto.InspectionDto;
import io.spd.csp.fieldmgmt.service.FieldService;
import io.spd.csp.fieldmgmt.web.model.InspectionsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

import static org.apache.commons.lang3.RandomStringUtils.randomAscii;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TechnicianController.class)
public class TechnicianControllerTest {

    private final Random random = new Random();

    private final Function<Integer, InspectionDto> generateInspection = techId -> new InspectionDto(random.nextInt(),
            random.nextInt(), TestDateUtil.pastDate().atStartOfDay(), TestDateUtil.futureDate().atStartOfDay(),
            InspectionDto.Status.PENDING, randomAscii(20), techId, null);

    @MockBean
    private FieldService fieldService;

    @Autowired
    private TechnicianController controller;

    @Test
    @WithMockUser(roles = "TECHNICIAN", username = "TEST")
    void shouldFetchListOfInspectionsToday() {
        // given: inspections
        List<InspectionDto> inspections = new ArrayList<>();
        inspections.add(generateInspection.apply(random.nextInt()));
        inspections.add(generateInspection.apply(random.nextInt()));
        when(fieldService.getInspections("TEST", LocalDate.now())).thenReturn(Flux.fromIterable(inspections));

        // when
        StepVerifier.FirstStep<InspectionsResponse> verifier = StepVerifier.create(controller.getInspectionsToday(
                Optional.of(LocalDate.now())));

        // then
        verifier.consumeNextWith(res -> {
            assertNotNull(res.getData());
            assertTrue(!res.getData().isEmpty());
            assertEquals(inspections.size(), res.getData().size());
        }).verifyComplete();
    }
}
