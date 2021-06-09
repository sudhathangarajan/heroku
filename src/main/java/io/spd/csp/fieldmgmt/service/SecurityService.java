package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.dto.UserDto;
import reactor.core.publisher.Mono;

public interface SecurityService {

    Mono<UserDto> getUserBySessionId(String sessionId);
}
