package com.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient //配置本应用将使用服务器注册和发现
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix //启用断路器，断路器依赖服务注册和发现
public class PlatAdminWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatAdminWebApplication.class, args);
	}
}
