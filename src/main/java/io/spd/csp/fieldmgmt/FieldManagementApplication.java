package io.spd.csp.fieldmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class FieldManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldManagementApplication.class, args);
    }

}
