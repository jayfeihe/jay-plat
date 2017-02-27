package com.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //作为注册和发现服务器
public class PlatEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatEurekaServerApplication.class, args);
	}
}
