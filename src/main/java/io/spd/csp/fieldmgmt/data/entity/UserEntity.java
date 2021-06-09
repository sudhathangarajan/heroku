package io.spd.csp.fieldmgmt.data.entity;

import io.spd.csp.fieldmgmt.dto.Role;
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
@Table("users")
public class UserEntity {

    @Id
    private Integer id;

    private String username;

    private String password;

    private String currentSessionId;

    private Role role;
}
