package com.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Cloud的统一配置中心服务器
 */
@SpringBootApplication
public class JayPlatConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JayPlatConfigServerApplication.class, args);
	}
}
