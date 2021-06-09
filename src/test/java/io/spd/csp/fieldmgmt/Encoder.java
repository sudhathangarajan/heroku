package io.spd.csp.fieldmgmt;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.UUID;

public class Encoder {

    public static void main(String... args) {
        var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var encodedPassword = passwordEncoder.encode("password");
        System.out.println(encodedPassword);
        System.out.println(UUID.randomUUID());
    }
}
