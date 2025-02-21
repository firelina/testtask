package ru.pln.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
public class TesttaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesttaskApplication.class, args);
    }

}
