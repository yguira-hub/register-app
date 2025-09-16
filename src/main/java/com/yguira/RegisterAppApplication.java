package com.yguira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RegisterAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterAppApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Hello from Register App! This is working!";
    }

    @GetMapping("/register")
    public String register() {
        return "User registration form coming soon!";
    }
}
