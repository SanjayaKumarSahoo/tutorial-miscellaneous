package com.learning.k8s;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learn")
public class HellController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/status")
    public String status() {
        return "UP";
    }
}
