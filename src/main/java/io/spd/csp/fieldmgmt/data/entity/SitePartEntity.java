package io.spd.csp.fieldmgmt.data.entity;

import io.spd.csp.fieldmgmt.dto.SitePartDto;
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
@Table("site_parts")
public class SitePartEntity {

    @Id
    private Integer id;

    private Integer siteId;

    private Integer partId;

    private SitePartDto.Integrity integrity;
}
