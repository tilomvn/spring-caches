package com.spring.caches.config;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

import com.spring.caches.entities.DemoEntity;

@AutoProtoSchemaBuilder(schemaPackageName = "com.spring.caches.protobuf", includeClasses = { DemoEntity.class })
interface DemoEntitySchemaBuilder extends GeneratedSchema {

}
