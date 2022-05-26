package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableEurekaClient
//设置启动服务发现
@EnableDiscoveryClient
//对hystrix熔断器的支持
@EnableCircuitBreaker
public class SpringCloudProviderDept8001_Hystrix_App {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProviderDept8001_Hystrix_App.class, args);
	}

}
