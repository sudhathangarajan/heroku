package io.spd.csp.fieldmgmt.config;

import io.spd.csp.fieldmgmt.Constants;
import io.spd.csp.fieldmgmt.dto.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.httpBasic();
        http.authorizeExchange(spec -> {
            spec.pathMatchers("/h2-console").permitAll();
            spec.pathMatchers(Constants.Web.TECHNICIAN_PATH + "/**").hasRole(Role.TECHNICIAN.name());
            spec.pathMatchers(Constants.Web.MANAGER_PATH + "/**").hasRole(Role.MANAGER.name());
            spec.pathMatchers("/data/**").hasRole("ADMIN");
            spec.anyExchange().authenticated();
        });
        // @formatter:on
        return http.build();
    }
}
