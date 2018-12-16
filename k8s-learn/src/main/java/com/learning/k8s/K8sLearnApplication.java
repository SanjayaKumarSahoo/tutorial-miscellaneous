package com.learning.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackageClasses = K8sLearnApplication.class)
public class K8sLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(K8sLearnApplication.class, args);
    }


}
