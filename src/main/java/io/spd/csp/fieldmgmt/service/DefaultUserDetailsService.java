package io.spd.csp.fieldmgmt.service;

import io.spd.csp.fieldmgmt.data.repo.UserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static io.spd.csp.fieldmgmt.Constants.Security.ROLE_PREFIX;

@Service
public record DefaultUserDetailsService(UserRepository userRepository) implements ReactiveUserDetailsService {

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).map(u -> new User(u.getUsername(), u.getPassword(),
                        List.of(() -> ROLE_PREFIX + u.getRole().name()))).cast(UserDetails.class)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found.")));
    }
}
