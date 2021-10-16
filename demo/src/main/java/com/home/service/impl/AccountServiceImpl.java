package com.home.service.impl;
/**
 * AccountServiceImpl
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.AccountDao;
import com.home.entity.Account;
import com.home.service.AccountService;




@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public int add(Account account) {
		return accountDao.add(account);
	}

	@Override
	public int edit(Account account) {
		return accountDao.edit(account);
	}

	@Override
	public int delete(Long id) {
		return accountDao.delete(id);
	}

	@Override
	public List<Account> findList(Map<String, Object> queryMap) {
		return accountDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return accountDao.getTotal(queryMap);
	}

	@Override
	public Account findById(Long id) {
		return accountDao.findById(id);
	}

	@Override
	public Account findByName(String name) {
		return accountDao.findByName(name);
	}

	@Override
	public int editBalance(Account account) {
		return accountDao.editBalance(account);
	}

}
