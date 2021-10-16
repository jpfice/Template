package com.system.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.system.entity.Company;

public interface CompanyService {
	
	//保存
	void save(Company company);
	
	//根据uuid删除记录
	void delete(String uuid);
	
	//修改
	@Transactional
	void update(Company company);
	
	//查询全部数据
	List<Company> findAll();
	
	//执行原生SQL语句查询
	List<Company> findByNativeSQL(String companyname);
	
	//简单分页查询
	//Page<Company> findAllSimplePage(Pageable pageable);
	
	//动态多条件的复杂查询

}
