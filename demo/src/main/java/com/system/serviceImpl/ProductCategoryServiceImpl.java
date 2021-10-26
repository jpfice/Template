package com.system.serviceImpl;
/**
 * ProductCategoryServiceImpl
 */
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.ProductCategoryDao;
import com.system.entity.ProductCategory;
import com.system.service.ProductCategoryService;


@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Override
	public int add(ProductCategory productCategory) {
		return productCategoryDao.add(productCategory);
	}

	@Override
	public int edit(ProductCategory productCategory) {
		return productCategoryDao.edit(productCategory);
	}

	@Override
	public int delete(Long id) {
		return productCategoryDao.delete(id);
	}

	@Override
	public List<ProductCategory> findList(Map<String, Object> queMap) {
		return productCategoryDao.findList(queMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		return productCategoryDao.getTotal(queryMap);
	}

	@Override
	public ProductCategory findById(Long id) {
		return productCategoryDao.findById(id);
	}

}
