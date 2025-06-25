package com.project.panini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaniniApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaniniApplication.class, args);
    }

}
