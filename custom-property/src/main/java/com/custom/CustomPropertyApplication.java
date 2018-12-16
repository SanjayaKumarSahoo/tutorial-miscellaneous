package com.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:external.xml")
public class CustomPropertyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomPropertyApplication.class, args);
    }
}
