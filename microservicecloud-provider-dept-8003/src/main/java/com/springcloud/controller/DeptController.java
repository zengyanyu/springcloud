package com.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.enttitys.Dept;
import com.springcloud.service.IDeptService;

@RestController
public class DeptController {

	@Autowired
	private IDeptService deptService;
	//设置服务发现
	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}

	@RequestMapping(value = "/dept/listAll", method = RequestMethod.GET)
	public List<Dept> listAll() {
		return deptService.listAll();
	}

	//服务发现
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("*****************" + list);

		List<ServiceInstance> serList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : serList) {
			System.out.println(element.getServiceId());
			System.out.println(element.getHost());
			System.out.println(element.getPort());
			System.out.println(element.getUri());
		}
		return this.client;
	}

}
