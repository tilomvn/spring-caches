package com.spring.caches.entities;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class DemoEntity {
	
	final String id;
	final String name;
	
	@ProtoFactory
	public DemoEntity(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@ProtoField(value = 1, required = true)
	public String getId() { return id; }
	
	@ProtoField(value = 2, required = true)
	@ProtoDoc("@Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)")
	public String getName() { return name; }
	
	@Override
	public boolean equals(Object o) {
		if (o == null) { return false; }
		if (!(o instanceof DemoEntity)) { return false; }
		
		DemoEntity e = (DemoEntity)o;
		return e.id.equals(id);
	}
}
