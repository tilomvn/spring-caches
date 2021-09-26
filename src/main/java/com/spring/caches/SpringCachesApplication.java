package com.spring.caches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.caches.services.LocalCacheService;
import com.spring.caches.services.RemoteCacheService;

@SpringBootApplication
public class SpringCachesApplication {

	Logger log = LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCachesApplication.class, args);
	}

	@Bean
	ApplicationRunner run(RemoteCacheService remoteCacheService, LocalCacheService localCacheService) {
		return args -> {
			log.info(remoteCacheService.getById(0).getName());
			log.info(remoteCacheService.getById(1).getName());
			log.info(remoteCacheService.getById(0).getName());
			
			log.info(localCacheService.getById(0));
			log.info(localCacheService.getById(1));
			log.info(localCacheService.getById(0));
			log.info(localCacheService.getById(0));
			log.info(localCacheService.getById(0));
			log.info(localCacheService.getById(0));
		};
	}
}
