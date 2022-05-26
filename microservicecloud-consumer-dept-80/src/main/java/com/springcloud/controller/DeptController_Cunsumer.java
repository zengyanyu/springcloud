package com.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springcloud.enttitys.Dept;

//消费者对象
@RestController
@SuppressWarnings("all")
public class DeptController_Cunsumer {

	//private static final String REST_URL_PREFIX = "http://localhost:8001";
	//改成微服务格式
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

	/**
	 * RestTemplate提供了多种便捷访问远程Http服务的方法
	   *   是一种简单便捷的访问Restful服务模板类,是Spring提供的用于访问Rest服务的客户端模板工具集
	 */
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(Dept detp) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", detp, Boolean.class);
	}

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
	}

	@RequestMapping(value = "/consumer/dept/listAll")
	public List<Dept> listAll() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/listAll", List.class);
	}

	// 测试@EnableDiscoveryClient,消费端可以调用服务发现
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}

}
