package io.spd.csp.fieldmgmt.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("reservations")
public class ReservationEntity {

    @Id
    private Integer id;

    private Integer partId;

    private Integer inspectionId;

    private Integer quantity;
}
