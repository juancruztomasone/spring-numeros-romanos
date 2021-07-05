package com.example.numeroromanosspring;

import org.springframework.boot.SpringApplication;
import com.example.numeroromanosspring.models.Numbers;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NumeroRomanosSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumeroRomanosSpringApplication.class, args);
        Numbers n = new Numbers();
    }

}
