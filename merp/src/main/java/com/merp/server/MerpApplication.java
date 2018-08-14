package com.merp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.merp.server.model" })
@EnableJpaRepositories(basePackages = { "com.merp.server.repository" })

public class MerpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerpApplication.class, args);
		System.out.println("In Main method");
	}
}
