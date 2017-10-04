package com.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StompApplication {


//TODO Spring + Local server without performance loss ( New Thread maybe? )
	public static void main(String[] args) {
		//SpringApplication.run(StompApplication.class, args);
		TSFRServer server = new TSFRServer();
		server.start();
	}
}
