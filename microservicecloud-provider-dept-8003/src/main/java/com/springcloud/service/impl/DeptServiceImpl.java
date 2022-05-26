package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.enttitys.Dept;
import com.springcloud.mapper.DeptMapper;
import com.springcloud.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public boolean add(Dept dept) {
		return deptMapper.add(dept);
	}

	@Override
	public Dept get(Long id) {
		return deptMapper.get(id);
	}

	@Override
	public List<Dept> listAll() {
		return deptMapper.listAll();
	}

}
