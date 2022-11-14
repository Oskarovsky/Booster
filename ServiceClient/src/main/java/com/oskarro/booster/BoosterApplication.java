package com.oskarro.booster;

import com.fasterxml.jackson.core.type.TypeReference;
import com.oskarro.booster.repository.UserRepository;
import com.oskarro.booster.service.ProductService;
import com.oskarro.booster.service.ProviderService;
import com.oskarro.booster.service.RoleService;
import com.oskarro.booster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.util.HashSet;

@SpringBootApplication
public class BoosterApplication {

	@Value("classpath:data/data-provider.json")
	Resource resourceFileProvider;

	@Value("classpath:data/data-product.json")
	Resource resourceFileProduct;

	@Value("classpath:data/data-meal.json")
	Resource resourceFileMeal;

	@Value("classpath:data/data-role.json")
	Resource resourceFileRole;

	@Value("classpath:data/data-user.json")
	Resource resourceFileUser;

	public static void main(String[] args) {
		SpringApplication.run(BoosterApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Bean
	@Profile("!test")
	CommandLineRunner runner(final ProviderService providerService,
							 final ProductService productService,
							 final UserService userService,
							 final RoleService roleService) {
		return args -> {
			roleService.loadDataFromJsonToDatabase(resourceFileRole, new TypeReference<>() {});
			userService.loadDataFromJsonToDatabase(resourceFileUser, new TypeReference<>() {});
			providerService.loadDataFromJsonToDatabase(resourceFileProvider, new TypeReference<>() {});
			productService.loadDataFromJsonToDatabase(resourceFileProduct, new TypeReference<>() {});

			loadUserRoles(userService, roleService);

//			mealService.loadDataFromJsonToDatabase(resourceFileMeal);
		};
	}

	private void loadUserRoles(UserService userService, RoleService roleService) {
		userService
				.get()
				.forEach(t -> {
					t.setRoles(new HashSet<>(roleService.get()));
					userRepository.save(t);
				});
	}

}
