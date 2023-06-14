package com.commerxo.commerxoconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CommerxoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerxoConfigServerApplication.class, args);
	}

}
