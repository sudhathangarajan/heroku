package io.spd.csp.fieldmgmt.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("spare_parts")
public class SparePartEntity {

    private Integer id;

    private Integer partId;

    private Integer quantity;
}
