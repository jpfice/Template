package com.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.system.entity.Company;

/**
 * 公司实现类dao
 * @author llq
 *
 */
@Repository
public interface CompanyDao {

	public int add(Company authority);
	public int deleteByRoleId(Long roleId);
	public List<Company> findListByRoleId(Long roleId);
}
