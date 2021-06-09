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
@Table("sites")
public class SiteEntity {

    @Id
    private Integer id;

    private String postalCode;

    private String description;
}
