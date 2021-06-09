package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.data.repo.UserRepository;
import io.spd.csp.fieldmgmt.dto.UserDto;
import io.spd.csp.fieldmgmt.mapper.UserMapperFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
record SecurityServiceImpl(UserRepository userRepository, UserMapperFactory userMapper, PasswordEncoder passwordEncoder)
        implements SecurityService {

    @Override
    public Mono<UserDto> getUserBySessionId(String sessionId) {
        return userRepository.findByCurrentSessionId(sessionId).map(userMapper.b2a());
    }
}
