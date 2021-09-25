package com.spring.caches.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.caches.config.CachesConfig;
import com.spring.caches.entities.DemoEntity;

@Service
@CacheConfig(cacheNames = CachesConfig.demoEntity)
public class TestService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<Integer, DemoEntity> database = new HashMap<>();
	
	TestService() {
		for (int i = 0; i < 3; i++) {
			database.put(i, new DemoEntity(UUID.randomUUID().toString(), "name" + i));
		}
	}
	
	@Cacheable
	public DemoEntity getById(int id) {
		log.info("Not in cache ...");
		return database.get(id);
	}
}
