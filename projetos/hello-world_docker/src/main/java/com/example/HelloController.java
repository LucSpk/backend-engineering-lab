package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello World! 🐳";
    }

    @GetMapping("/api/hello")
    public Hello helloJson() {
        return new Hello("Hello World!", "Docker + Spring Boot");
    }

    public static class Hello {
        public String message;
        public String technology;

        public Hello(String message, String technology) {
            this.message = message;
            this.technology = technology;
        }

        public String getMessage() {
            return message;
        }

        public String getTechnology() {
            return technology;
        }
    }
}
