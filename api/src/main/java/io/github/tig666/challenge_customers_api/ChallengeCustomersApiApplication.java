package io.github.tig666.challenge_customers_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ChallengeCustomersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeCustomersApiApplication.class, args);
	}

	@Bean
	CommandLineRunner printInfo(Environment env) {
		return args -> {

			String port = env.getProperty("server.port");
			String appName = env.getProperty("spring.application.name");
			String message = env.getProperty("app.environment.message");

			System.out.println("=================================");
			System.out.println("Puerto: " + port);
			System.out.println("Nombre app: " + appName);
			System.out.println("Mensaje log: " + message);
			System.out.println("=================================");
		};
	}

}
