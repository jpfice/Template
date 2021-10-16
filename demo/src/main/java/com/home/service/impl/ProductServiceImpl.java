package com.home.service.impl;
/**
 * ProductServiceImpl
 */
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.ProductDao;
import com.home.entity.Product;
import com.home.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public int add(Product product) {
		return productDao.add(product);
	}

	@Override
	public int edit(Product product) {
		return productDao.edit(product);
	}

	@Override
	public int delete(Long id) {
		return productDao.delete(id);
	}

	@Override
	public List<Product> findList(Map<String, Object> queMap) {
		return productDao.findList(queMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return productDao.getTotal(queryMap);
	}

	@Override
	public Product findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public int updateNum(Product product) {
		return productDao.updateNum(product);
	}

}
