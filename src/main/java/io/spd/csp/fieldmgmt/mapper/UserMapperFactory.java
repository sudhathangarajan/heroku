package io.spd.csp.fieldmgmt.mapper;

import io.spd.csp.fieldmgmt.data.entity.UserEntity;
import io.spd.csp.fieldmgmt.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapperFactory implements MapperFactory<UserDto, UserEntity> {

    @Override
    public Function<UserDto, UserEntity> a2b() {
        return dto -> new UserEntity(null, dto.username(), dto.currentSessionId(),
                dto.currentSessionId(), dto.role());
    }

    @Override
    public Function<UserEntity, UserDto> b2a() {
        return ent -> new UserDto(ent.getUsername(), ent.getCurrentSessionId(), ent.getRole());
    }
}
