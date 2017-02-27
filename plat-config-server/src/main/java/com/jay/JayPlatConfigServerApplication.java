package com.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Cloud的统一配置中心服务器
 */
@SpringBootApplication
@EnableConfigServer  //作为配置服务器
public class JayPlatConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JayPlatConfigServerApplication.class, args);
	}
}
