package com.home.service.impl;
/**
 * �ջ���ַ�ӿ�ʵ����
 */
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.AddressDao;
import com.home.entity.Address;
import com.home.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public int add(Address address) {
		return addressDao.add(address);
	}

	@Override
	public int edit(Address address) {
		return addressDao.edit(address);
	}

	@Override
	public int delete(Long id) {
		return addressDao.delete(id);
	}

	@Override
	public List<Address> findList(Map<String, Object> queMap) {
		return addressDao.findList(queMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return addressDao.getTotal(queryMap);
	}

	@Override
	public Address findById(Long id) {
		return addressDao.findById(id);
	}

}
