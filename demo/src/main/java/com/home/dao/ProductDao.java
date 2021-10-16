package com.home.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.home.entity.Product;
/**
 * ProductDao
 * @author Administrator
 *
 */
@Repository
public interface ProductDao {
	/**
	 * �����Ʒ
	 * @param productCategory
	 * @return
	 */
	public int add(Product product);
	
	/**
	 * �༭��Ʒ
	 * @param productCategory
	 * @return
	 */
	public int edit(Product product);
	
	/**
	 * ɾ����Ʒ
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * �����������ʲ�ѯ��Ʒ
	 * @param queMap
	 * @return
	 */
	public List<Product> findList(Map<String, Object> queryMap);
	
	/**
	 * ��ȡ�����������ܼ�¼��
	 * @param queryMap
	 * @return
	 */
	public Integer getTotal(Map<String, Object> queryMap);
	
	/**
	 * ����id��ѯ��Ʒ
	 * @param id
	 * @return
	 */
	public Product findById(Long id);
	
	/**
	 * ����ͳ����Ϣ
	 * @param product
	 * @return
	 */
	public int updateNum(Product product);
}
