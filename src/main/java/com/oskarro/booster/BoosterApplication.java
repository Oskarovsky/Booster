package com.oskarro.booster;

import com.oskarro.booster.service.ProviderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoosterApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ProviderService providerService) {
		return args -> {
			providerService.loadDataFromJsonToDatabase("data/data-provider.json");
		};
	}

}
