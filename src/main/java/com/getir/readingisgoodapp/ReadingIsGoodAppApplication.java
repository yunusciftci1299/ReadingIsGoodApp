package com.getir.readingisgoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.getir.readingisgoodapp"})
@EntityScan("com.getir.readingisgoodapp")
@EnableJpaRepositories("com.getir.readingisgoodapp")
public class ReadingIsGoodAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingIsGoodAppApplication.class, args);
	}

}
