package com.spring.caches.config;

import java.nio.file.Paths;
import java.util.Arrays;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.remote.provider.SpringRemoteCacheManager;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CachesConfig {

	public static final String demoEntity = "demoEntity";
	public static final String localCache = "localCache";
	
	@Bean("remoteCaches")
	InfinispanRemoteCacheCustomizer remoteCaches() {
		return rc -> {
			rc.remoteCache(demoEntity).configurationURI(Paths.get("src", "main", "resources", "demoEntityCache.xml").toUri());
			rc.remoteCache(demoEntity).marshaller(ProtoStreamMarshaller.class);
            rc.addContextInitializer(new DemoEntitySchemaBuilderImpl());
            rc.addServer().host("127.0.0.1").port(11222)
			  .security().authentication().username("admin").password("admin");
		};
	}
	
	@Bean("localCaches")
	CacheManager localCaches() {
		return new ConcurrentMapCacheManager(localCache);
	}
	
	@Bean
	@Primary
	CompositeCacheManager compositeCacheManager(@Qualifier("springRemoteCacheManager") SpringRemoteCacheManager springRemoteCacheManager,
			                                    @Qualifier("localCaches") CacheManager localCacheManager) {
		CompositeCacheManager c = new CompositeCacheManager();
		c.setCacheManagers(Arrays.asList(springRemoteCacheManager, localCacheManager));
		return c;
	}
}
