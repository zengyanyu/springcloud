package com.springcloud.service;

import java.util.List;

import com.springcloud.enttitys.Dept;

public interface IDeptService {

	Dept get(Long id);

	List<Dept> listAll();

	boolean add(Dept dept);

}
