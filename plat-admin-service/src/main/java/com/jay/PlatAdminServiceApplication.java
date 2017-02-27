package com.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * Dao层使用了MyBatis+Batis-Spring的依赖，使用Druid作为连接池
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PlatAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatAdminServiceApplication.class, args);
	}
}
