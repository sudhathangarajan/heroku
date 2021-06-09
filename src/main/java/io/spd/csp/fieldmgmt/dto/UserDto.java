package io.spd.csp.fieldmgmt.dto;

import lombok.Builder;

@Builder
public record UserDto(String username, String currentSessionId, Role role) {}
