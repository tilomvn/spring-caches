package com.spring.caches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.caches.services.TestService;

@SpringBootApplication
public class SpringCachesApplication {

	Logger log = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCachesApplication.class, args);
	}

	@Bean
	ApplicationRunner run(TestService service) {
		return args -> {
			log.info(service.getById(0).getName());
			log.info(service.getById(1).getName());
			log.info(service.getById(0).getName());
		};
	}
}
