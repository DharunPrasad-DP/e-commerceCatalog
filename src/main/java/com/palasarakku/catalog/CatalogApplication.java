package com.palasarakku.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
public class CatalogApplication {

	/**
	 * Main method of the spring boot application
	 *
	 * @param args - arguments
	 */

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

}
