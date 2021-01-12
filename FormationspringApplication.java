package com.formation.formationspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.formation.formationspring.*")
public class FormationspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormationspringApplication.class, args);
	}

}
