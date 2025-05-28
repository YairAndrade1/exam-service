package com.example.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExamenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenServiceApplication.class, args);
	}

}
