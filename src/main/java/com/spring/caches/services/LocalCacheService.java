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

@Service
@CacheConfig(cacheNames = CachesConfig.localCache, cacheManager = "localCaches")
public class LocalCacheService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<Integer, String> databases = new HashMap<>();
	
	LocalCacheService() {
		for (int i = 0; i < 4; i++) {
			databases.put(i, UUID.randomUUID().toString());
		}
	}
	
	@Cacheable
	public String getById(int id) {
		log.info("Not in cache ...");
		return databases.get(id);
	}
}
