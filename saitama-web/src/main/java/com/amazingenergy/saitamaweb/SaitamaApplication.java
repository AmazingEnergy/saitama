package com.amazingenergy.saitamaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.amazingenergy"})
public class SaitamaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaitamaApplication.class, args);
    }
}
