package com.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class ConfigBean {

	@Bean
	@LoadBalanced //实现客户端负载均衡(默认使用的轮询算法)
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	//使用随机算法
	@Bean
	public IRule myRule() {
		//return new RoundRobinRule();//轮询算法
		//达到的目的:用我们重新选择的随机算法(RandomRule)替代默认的轮询.
		return new RandomRule();
		//return new AvailabilityFilteringRule();
		//return new WeightedResponseTimeRule();
		//return new RetryRule();
		//return new BestAvailableRule();
		//return new ZoneAvoidanceRule();
	}

}
