package com.insanity.inmem.server;

import static spark.Spark.*;
import spark.*;

public class HelloSpark {
	public static void main(String[] args) {
		// get("/hello", (req, res) -> "Hello World");
		get(new Route("/hello") {
			@Override
			public Object handle(Request request, Response response) {
				return "Hello Spark MVC Framework!";
			}
		});
	}
}
