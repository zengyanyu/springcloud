package com.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

//自定义算法
@Configuration
public class MySelfRule {

	@Bean
	public IRule myRule() {
		//Ribbon默认为轮询,我自定义为
		//return new RandomRule();
		return new RandomRuleZYY();//使用自己自定义的算法,
	}

}
