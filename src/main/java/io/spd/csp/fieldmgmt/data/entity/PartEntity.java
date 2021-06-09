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
@Table("parts")
public class PartEntity {

    @Id
    private Integer id;

    private String name;

    private Integer available;
}
