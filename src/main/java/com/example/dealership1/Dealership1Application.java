package com.example.dealership1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Dealership1Application {

    public static void main(String[] args) {
        SpringApplication.run(Dealership1Application.class, args);
    }

}
