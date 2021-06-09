package io.spd.csp.fieldmgmt.web.controller;

import io.spd.csp.fieldmgmt.Constants;
import io.spd.csp.fieldmgmt.TestDateUtil;
import io.spd.csp.fieldmgmt.dto.InspectionDto;
import io.spd.csp.fieldmgmt.dto.Role;
import io.spd.csp.fieldmgmt.service.FieldService;
import io.spd.csp.fieldmgmt.web.model.InspectionsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
class ManagementControllerTest {

    private final Random random = new Random();

    private final Function<Integer, InspectionDto> generateInspection = techId -> new InspectionDto(random.nextInt(),
            random.nextInt(), TestDateUtil.pastDate().atStartOfDay(), TestDateUtil.futureDate().atStartOfDay(),
            InspectionDto.Status.PENDING, randomAscii(20), techId, null);

    @MockBean
    private FieldService fieldService;

    @MockBean
    private ReactiveUserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebTestClient client;

    @Test
    @WithMockUser(roles = { "MANAGER" })
    void shouldFetchInspectionsToday() {
        // given: User
        var username = "TEST";
        var user = User.builder().username(username).password("password").roles(Role.MANAGER.name()).build();
        when(userDetailsService.findByUsername(username)).thenReturn(Mono.just(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // given: inspections
        List<InspectionDto> inspections = new ArrayList<>();
        inspections.add(generateInspection.apply(random.nextInt()));
        inspections.add(generateInspection.apply(random.nextInt()));
        when(fieldService.getInspections("%", LocalDate.now())).thenReturn(Flux.fromIterable(inspections));

        client.get().uri(Constants.Web.MANAGER_PATH + "/inspections")
                .headers(headers -> {
                    headers.setBasicAuth(username, "password");
                })
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(InspectionsResponse.class)
                .consumeWith(result -> {
                    InspectionsResponse res = result.getResponseBody();
                    assertNotNull(res.getData());
                    assertTrue(!res.getData().isEmpty());
                    assertEquals(inspections.size(), res.getData().size());
                });
    }
}