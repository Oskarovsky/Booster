package com.oskarro.booster;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oskarro.booster.service.ProductService;
import com.oskarro.booster.service.ProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class BoosterApplication {

	@Value("classpath:data/data-provider.json")
	Resource resourceFileProvider;

	@Value("classpath:data/data-product.json")
	Resource resourceFileProduct;

	@Value("classpath:data/data-meal.json")
	Resource resourceFileMeal;

	public static void main(String[] args) {
		SpringApplication.run(BoosterApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner runner(final ProviderService providerService,
							 final ProductService productService) {
		return args -> {
			providerService.loadDataFromJsonToDatabase(resourceFileProvider, new TypeReference<>() {});
			productService.loadDataFromJsonToDatabase(resourceFileProduct, new TypeReference<>() {});
//			mealService.loadDataFromJsonToDatabase(resourceFileMeal);
		};
	}

}
