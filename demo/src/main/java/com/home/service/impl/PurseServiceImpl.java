package com.home.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.PurseDao;
import com.home.entity.Purse;
import com.home.service.PurseService;


@Service
public class PurseServiceImpl implements PurseService {

	@Autowired
	private PurseDao purseDao;

	@Override
	public int add(Purse purse) {
		return purseDao.add(purse);
	}

	@Override
	public List<Purse> findById(Long id) {
		return purseDao.findById(id);
	}

	@Override
	public List<Purse> findRecord(Map<String,Object> queryMap) {
		return purseDao.findRecord(queryMap);
	}

	@Override
	public List<Purse> findList(Map<String, Object> queryMap) {
		return purseDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		return purseDao.getTotal(queryMap);
	}

	@Override
	public int examine(Purse purse) {
		return purseDao.examine(purse);
	}

	@Override
	public int editBalance(Map<String, Object> paramMap) {
		return purseDao.editBalance(paramMap);
	}

	@Override
	public Purse findListByid(Integer id) {
		return purseDao.findListByid(id);
	}

}
