package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ElasticSearchConfiguration.class})
public class BootAppLoader {
	public static void main(String[] args) {
		SpringApplication.run(BootAppLoader.class, args);
	}
}
