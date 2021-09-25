package com.spring.caches.config;

import java.nio.file.Paths;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@EnableCaching
public class CachesConfig {

	public static final String demoEntity = "demoEntity";

	/*@Bean
	RemoteCacheManager cacheManager() {
		ConfigurationBuilder clientBuilder = new ConfigurationBuilder();
		clientBuilder.remoteCache(demoEntity).configurationURI(Paths.get("src", "main", "resources", "demoEntityCache.xml").toUri())
					 .marshaller(ProtoStreamMarshaller.class)
					 .addContextInitializer(new DemoEntitySchemaBuilderImpl());
		clientBuilder.addServer().host("127.0.0.1").port(11222)
					 .security().authentication().username("admin").password("admin");
		RemoteCacheManager remoteCacheManager = new RemoteCacheManager(clientBuilder.build());
		return remoteCacheManager;
	}*/
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	InfinispanRemoteCacheCustomizer demoEntity() {
		return rc -> {
			rc.remoteCache(demoEntity).configurationURI(Paths.get("src", "main", "resources", "demoEntityCache.xml").toUri())
			                          .marshaller(ProtoStreamMarshaller.class);
			rc.addContextInitializer(new DemoEntitySchemaBuilderImpl());
			rc.addServer().host("127.0.0.1").port(11222)
			  .security().authentication().username("admin").password("admin");
		};
	}
}
