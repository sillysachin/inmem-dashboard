package com.insanity.inmem.core;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;

import com.hazelcast.config.Config;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

public class InMemNode {
	private static final String[] DATA_RESOURCES_TO_LOAD = { "text1.txt",
			"text2.txt", "text3.txt" };

	public static void main(String[] args) throws Exception {
		Config cfg = new Config();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		JobTracker jobTracker = instance.getJobTracker("default");

		Map<Integer, String> mapCustomers = instance.getMap("customers");
		mapCustomers.put(1, "Joe");
		mapCustomers.put(2, "Ali");
		mapCustomers.put(3, "Avi");

		System.out.println("Customer with key 1: " + mapCustomers.get(1));
		System.out.println("Map Size:" + mapCustomers.size());

		Queue<String> queueCustomers = instance.getQueue("customers");
		queueCustomers.offer("Tom");
		queueCustomers.offer("Mary");
		queueCustomers.offer("Jane");
		System.out.println("First customer: " + queueCustomers.poll());
		System.out.println("Second customer: " + queueCustomers.peek());
		System.out.println("Queue size: " + queueCustomers.size());

		// a multimap to hold <customerId, Order> pairs
		MultiMap<String, Order> mmCustomerOrders = instance
				.getMultiMap("customerOrders");
		mmCustomerOrders.put("1", new Order("iPhone", 340));
		mmCustomerOrders.put("1", new Order("MacBook", 1200));
		mmCustomerOrders.put("1", new Order("iPod", 79));

		// get orders of the customer with customerId 1.
		Collection<Order> colOrders = mmCustomerOrders.get("1");
		for (Order order : colOrders) {
			System.out.println("Customer Orders: " + order.toString());
		}

		// remove specific key/value pair
		// boolean removed = mmCustomerOrders
		// .remove("1", new Order("iPhone", 340));
		System.out.println("CustomerOrders size: " + mmCustomerOrders.size());
	}
}