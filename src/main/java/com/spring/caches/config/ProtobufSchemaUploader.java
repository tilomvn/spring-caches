package com.spring.caches.config;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class ProtobufSchemaUploader {

	Logger log = LoggerFactory.getLogger(getClass());
	
	ProtobufSchemaUploader(RemoteCacheManager cacheManager) {
		RemoteCache<String, String> metaDataCache = cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
//		RemoteCache<String, String> metaDataCache = cacheManager.getCache(CachesConfig.demoEntity);
		//DemoEntitySchemaBuilderImpl would be generated at compile time.
		GeneratedSchema schema = new DemoEntitySchemaBuilderImpl();
		metaDataCache.put(schema.getProtoFileName(), schema.getProtoFile());
	}
}
