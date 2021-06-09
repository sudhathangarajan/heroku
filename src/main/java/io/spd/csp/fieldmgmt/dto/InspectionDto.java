package io.spd.csp.fieldmgmt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InspectionDto(Integer id, Integer siteId,
                            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime scheduledAt,
                            LocalDateTime completedAt, Status status, String remarks, Integer technicianId,
                            Integer previousInspectionId) {

    public enum Status {

        PENDING,

        COMPLETED,

        REVISIT;
    }
}
