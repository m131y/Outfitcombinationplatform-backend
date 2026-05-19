package com.my131.Outfitcombinationplatform_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OutfitcombinationplatformBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutfitcombinationplatformBackendApplication.class, args);
	}

}
