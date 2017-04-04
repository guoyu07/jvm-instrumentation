package net.piotrl.jvm.service;

import spark.Request;
import spark.Response;

import java.util.Objects;

import static spark.Spark.get;

public class App {

	public static void main(String[] args) {
		get("/hello", (req, res) -> {
			Thread.sleep(100);
			return "Hello world";
		});

		get("/param/:name", App::getJoe);
	}

	@PerformanceAudit
	public static Object getJoe(Request req, Response res) throws InterruptedException {
		System.out.println("getJoe method");
		String name = req.params(":name");
		if (Objects.equals(name, "Joe")) {
            Thread.sleep(1000);
            return "Joe";
        }

		return "Fast: " + name;
	}

}
