package com.system.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.system.entity.Authority;

/**
 * AuthorityDao
 * @author jpf
 *
 */
@Repository
public interface AuthorityDao {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}
