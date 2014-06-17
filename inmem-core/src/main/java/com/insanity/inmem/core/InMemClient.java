package com.insanity.inmem.core;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;

public class InMemClient {

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		HazelcastInstance client = HazelcastClient
				.newHazelcastClient(clientConfig);
		IMap map = client.getMap("customers");
		MultiMap<String, Order> multiMap = client.getMultiMap("customerOrders");
		System.out.println("Map Size:" + map.size());
		System.out.println("MultiMap Size:" + multiMap.size());
	}
}