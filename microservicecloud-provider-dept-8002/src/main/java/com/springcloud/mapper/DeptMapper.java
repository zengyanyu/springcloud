package com.springcloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springcloud.enttitys.Dept;

@Mapper
public interface DeptMapper {

	Dept get(Long id);

	List<Dept> listAll();

	boolean add(Dept dept);

}
